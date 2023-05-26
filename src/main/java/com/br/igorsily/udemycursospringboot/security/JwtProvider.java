package com.br.igorsily.udemycursospringboot.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.igorsily.udemycursospringboot.dtos.v1.UserAuthenticationDTO;
import com.br.igorsily.udemycursospringboot.entitys.Permission;
import com.br.igorsily.udemycursospringboot.exceptions.InvalidJwtAuthenticationException;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expiration-in-seconds:3600}")
    private long validityInSeconds = 3600; // 1h

    private final UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public UserAuthenticationDTO createToken(String username, Set<Permission> permissions) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validity = LocalDateTime.now().plusSeconds(validityInSeconds);

        String token = getToken(username, List.copyOf(permissions), now, validity);
        String refreshToken = getRefreshToken(username, List.copyOf(permissions), now);

        return UserAuthenticationDTO.builder()
                .username(username)
                .token(token)
                .refreshToken(refreshToken)
                .createdAt(now)
                .expiredAt(validity)
                .build();
    }

    public UserAuthenticationDTO refreshToken(String refreshToken) {
        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            refreshToken  = refreshToken.substring(7);
        } else {
            throw new InvalidJwtAuthenticationException("Refresh token is invalid");
        }

        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(refreshToken);

        String username = decodedJWT.getSubject();
        Set<Permission> permissions = decodedJWT.getClaim("permissions").asList(String.class).stream().map(Permission::new).collect(Collectors.toSet());

       return createToken(decodedJWT.getSubject(), permissions);
    }

    private String getToken(String username, List<Permission> permissions, LocalDateTime now, LocalDateTime validity) {

        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create()
                .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(validity.atZone(ZoneId.systemDefault()).toInstant()))
                .withIssuer(issuerUrl)
                .withSubject(username)
                .withClaim("permissions", permissions.stream().map(Permission::getDescription).collect(Collectors.toList()))
                .sign(algorithm)
                .strip();

    }

    private String getRefreshToken(String username, List<Permission> permissions, LocalDateTime now) {

        LocalDateTime refreshValidity = LocalDateTime.now().plusSeconds(validityInSeconds * 2);

        return JWT.create()
                .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(refreshValidity.atZone(ZoneId.systemDefault()).toInstant()))
                .withSubject(username)
                .withClaim("permissions", permissions.stream().map(Permission::getDescription).collect(Collectors.toList()))
                .sign(algorithm)
                .strip();

    }

    public Authentication getAuthentication(String token) {

        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {

        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

        try {
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

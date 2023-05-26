package com.br.igorsily.udemycursospringboot.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.br.igorsily.udemycursospringboot.dtos.v1.UserAuthenticationDTO;
import com.br.igorsily.udemycursospringboot.dtos.v1.UserLoginDTO;
import com.br.igorsily.udemycursospringboot.entitys.User;
import com.br.igorsily.udemycursospringboot.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final UserService userService;

    public ResponseEntity<UserAuthenticationDTO> login(UserLoginDTO userLoginDTO) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword()));
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
        }

        User user = userService.findByUsername(userLoginDTO.getUsername());

        UserAuthenticationDTO userAuthentication = jwtProvider.createToken(userLoginDTO.getUsername(), user.getPermissions());

        return new ResponseEntity<>(userAuthentication, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserAuthenticationDTO> refreshToken(String refreshToken) {

        UserAuthenticationDTO userAuthentication = jwtProvider.refreshToken(refreshToken);

        userService.findByUsername(userAuthentication.getUsername());

        return new ResponseEntity<>(userAuthentication, HttpStatus.OK);


    }
}

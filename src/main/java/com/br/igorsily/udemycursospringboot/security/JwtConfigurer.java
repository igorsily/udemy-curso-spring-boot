package com.br.igorsily.udemycursospringboot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;


    @Override
    public void configure(HttpSecurity builder) throws Exception {

        JwtFilter customFilter = new JwtFilter(jwtProvider);

        builder.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

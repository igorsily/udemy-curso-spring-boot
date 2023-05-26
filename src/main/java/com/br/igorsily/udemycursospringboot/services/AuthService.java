package com.br.igorsily.udemycursospringboot.services;

import com.br.igorsily.udemycursospringboot.dtos.v1.UserAuthenticationDTO;
import com.br.igorsily.udemycursospringboot.dtos.v1.UserLoginDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<UserAuthenticationDTO> login(UserLoginDTO userLoginDTO);

    ResponseEntity<UserAuthenticationDTO> refreshToken(String refreshToken);
}

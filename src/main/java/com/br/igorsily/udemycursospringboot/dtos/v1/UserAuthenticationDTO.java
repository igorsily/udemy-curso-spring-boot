package com.br.igorsily.udemycursospringboot.dtos.v1;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    @Builder.Default
    private Boolean authenticated = true;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime expiredAt;

    private String token;

    private String refreshToken;

}

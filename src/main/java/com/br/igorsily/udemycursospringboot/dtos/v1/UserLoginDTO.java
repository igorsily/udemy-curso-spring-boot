package com.br.igorsily.udemycursospringboot.dtos.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}

package com.br.igorsily.udemycursospringboot.controllers;

import com.br.igorsily.udemycursospringboot.dtos.v1.UserAuthenticationDTO;
import com.br.igorsily.udemycursospringboot.dtos.v1.UserLoginDTO;
import com.br.igorsily.udemycursospringboot.exceptions.ExceptionResponse;
import com.br.igorsily.udemycursospringboot.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Auth API")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Authentication", description = "Authentication User and Return Token", tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authentication Success",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserAuthenticationDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
            })
    @PostMapping("/login")
    public ResponseEntity<UserAuthenticationDTO> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
       return this.authService.login(userLoginDTO);
    }

    @Operation(summary = "Authentication Refresh Token", description = "Authentication  Refresh Token", tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Refresj Token Success",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserAuthenticationDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    ),
            })
    @PutMapping("/refresh-token")
    public ResponseEntity<UserAuthenticationDTO> refreshToken(@RequestHeader("Authorization") @NotNull String refreshToken) {
        return this.authService.refreshToken(refreshToken);
    }
}

package com.br.igorsily.udemycursospringboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Slf4j
public class UdemyCursoSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UdemyCursoSpringBootApplication.class, args);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String result = passwordEncoder.encode("123456");

        log.info("Password: " + result);
    }

}

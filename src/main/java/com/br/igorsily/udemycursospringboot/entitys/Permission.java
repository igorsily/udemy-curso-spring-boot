package com.br.igorsily.udemycursospringboot.entitys;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@Table(name = "permissions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements GrantedAuthority,Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    public Permission(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.description;
    }
}

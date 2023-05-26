package com.br.igorsily.udemycursospringboot.dtos.v1;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "lastName", "firstName", "gender", "address"})
public class PersonDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    @JsonProperty("first_name")
    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    //    @JsonIgnore
    @Builder.Default
    private Boolean enabled = true;

}

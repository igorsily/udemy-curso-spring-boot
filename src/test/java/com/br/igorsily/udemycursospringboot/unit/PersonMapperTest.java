package com.br.igorsily.udemycursospringboot.unit;

import com.br.igorsily.udemycursospringboot.dtos.v1.PersonDTO;
import com.br.igorsily.udemycursospringboot.entitys.Person;
import com.br.igorsily.udemycursospringboot.mappers.PersonMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonMapperTest {

    @Test
    public void testGivenPersonDTOThenReturnPersonEntity() {

        PersonDTO personDTO = new PersonDTO().builder()
                .firstName("Igor")
                .lastName("Sily")
                .address("Rua ABC")
                .gender("MASCULINO")
                .build();

        Person person = PersonMapper.INSTANCE.toEntity(personDTO);

        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isEqualTo(personDTO.getFirstName());
        assertThat(person.getLastName()).isEqualTo(personDTO.getLastName());
        assertThat(person.getAddress()).isEqualTo(personDTO.getAddress());
        assertThat(person.getGender()).isEqualTo(personDTO.getGender());

    }

    @Test
    public void testGivenPersonEntityThenReturnDTO() {

        Person person = new Person().builder()
                .firstName("Igor")
                .lastName("Sily")
                .address("Rua ABC")
                .gender("MASCULINO")
                .build();

        PersonDTO personDTO = PersonMapper.INSTANCE.toDTO(person);

        assertThat(personDTO).isNotNull();
        assertThat(personDTO.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(personDTO.getLastName()).isEqualTo(person.getLastName());
        assertThat(personDTO.getAddress()).isEqualTo(person.getAddress());
        assertThat(personDTO.getGender()).isEqualTo(person.getGender());

    }
}

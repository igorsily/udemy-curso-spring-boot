package com.br.igorsily.udemycursospringboot.services;

import com.br.igorsily.udemycursospringboot.dtos.v1.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PersonService {

    Page<PersonDTO>  findAll(Pageable pageable);

    PersonDTO findById(UUID id);

    PersonDTO save(PersonDTO personDTO);

    PersonDTO update(UUID id ,PersonDTO personDTO);

    void delete(UUID id);

    Page<PersonDTO> findByName(String firstName, Pageable pageable);
}

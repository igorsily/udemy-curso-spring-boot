package com.br.igorsily.udemycursospringboot.services;

import com.br.igorsily.udemycursospringboot.dtos.v1.PersonDTO;
import com.br.igorsily.udemycursospringboot.entitys.Person;
import com.br.igorsily.udemycursospringboot.exceptions.EntityNotFoundException;
import com.br.igorsily.udemycursospringboot.mappers.PersonMapper;
import com.br.igorsily.udemycursospringboot.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Page<PersonDTO> findAll(Pageable pageable) {

        Page<Person> personPage = personRepository.findAll(pageable);

        return personPage.map(PersonMapper.INSTANCE::toDTO);
    }

    @Override
    public PersonDTO findById(UUID id) {

        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person not found"));

        return PersonMapper.INSTANCE.toDTO(person);
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.toEntity(personDTO);

        person = personRepository.save(person);

        return PersonMapper.INSTANCE.toDTO(person);
    }

    @Override
    public PersonDTO update(UUID id, PersonDTO personDTO) {

        this.findById(id);

        Person person = PersonMapper.INSTANCE.toEntity(personDTO);

        person = person.builder()
                .id(id)
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .address(personDTO.getAddress())
                .gender(personDTO.getGender())
                .build();

        person = personRepository.save(person);

        return PersonMapper.INSTANCE.toDTO(person);
    }

    @Override
    public void delete(UUID id) {

        this.findById(id);

        personRepository.deleteById(id);

    }

    @Override
    public Page<PersonDTO> findByName(String firstName, Pageable pageable) {

        Page<Person> personPage = personRepository.findByFirstNameContainingIgnoreCase(firstName, pageable);

        return personPage.map(PersonMapper.INSTANCE::toDTO);
    }
}

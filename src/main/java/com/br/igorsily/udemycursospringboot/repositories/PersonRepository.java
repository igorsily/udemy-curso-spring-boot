package com.br.igorsily.udemycursospringboot.repositories;

import com.br.igorsily.udemycursospringboot.entitys.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

//    @Query("SELECT p FROM Person p WHERE p.firstName LIKE CONCAT ('%',:firstName,'%')")
//    Page<Person> findByFirstNameLike(@Param("username") String firstName, Pageable pageable);

//    @Query("SELECT p FROM Person p WHERE p.firstName LIKE LOWER '%',:firstName,'%'))")
    Page<Person> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

}

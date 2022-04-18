package com.backend.finalProject.persistence.repository;

import com.backend.finalProject.persistence.entities.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist, Integer> {

    //Ejemplo de una query en el repository
    @Query("UPDATE Dentist d set d.name = ?1")
    Optional<Dentist> updateDentistName(String name);

}

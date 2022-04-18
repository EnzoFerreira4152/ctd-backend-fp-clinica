package com.backend.finalProject.persistence.repository;

import com.backend.finalProject.persistence.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Integer> {
}

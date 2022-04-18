package com.backend.finalProject.persistence.repository;

import com.backend.finalProject.persistence.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {
}

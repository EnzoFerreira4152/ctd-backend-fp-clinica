package com.backend.finalProject.persistence.repository;

import com.backend.finalProject.persistence.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
}

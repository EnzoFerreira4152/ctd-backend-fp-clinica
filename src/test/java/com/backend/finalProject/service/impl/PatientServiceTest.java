package com.backend.finalProject.service.impl;

import com.backend.finalProject.exceptions.ResourceNotFoundException;
import com.backend.finalProject.model.AddressDTO;
import com.backend.finalProject.model.PatientDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PatientServiceTest {

    @Autowired
    PatientService patientService;

    @BeforeAll
    void setUp() {
        AddressDTO address = new AddressDTO();
        address.setStreet("Salta");
        address.setNumber(123);
        address.setCity("Arroyo");
        address.setCounty("Córdoba");

        PatientDTO patient = new PatientDTO();
        patient.setName("Romina");
        patient.setLastName("García");
        patient.setDNI(12345678);
        patient.setDischargeDate(LocalDate.now());
        patient.setAddress(address);

        patientService.addPatient(patient);

    }

    @Test
    void addPatient() {
        AddressDTO address = new AddressDTO();
        address.setStreet("Necochea");
        address.setNumber(123);
        address.setCity("Buenos Aires");
        address.setCounty("Buenos Aires");

        PatientDTO patient = new PatientDTO();
        patient.setName("Juan");
        patient.setLastName("Perez");
        patient.setDNI(12345678);
        patient.setDischargeDate(LocalDate.now());
        patient.setAddress(address);

        PatientDTO response = patientService.addPatient(patient);
        assertNotNull(response);
    }

    @Test
    void listAllPatients() {
        Set<PatientDTO> list = patientService.listAllPatients();
        assertTrue(list.size() > 0);
    }

    @Test
    void findPatientById() {
        try{
            PatientDTO result = patientService.findPatientById(1);
            assertNotNull(result);
        } catch (ResourceNotFoundException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Test
    void modifyPatient() {
        try {
            PatientDTO patient = patientService.findPatientById(1);
            patient.setName("Rocío");
            patient.setDNI(38254175);

            patientService.modifyPatient(patient);

            assertEquals("Rocío", patient.getName());
            assertEquals(38254175, patient.getDNI());
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void deletePatient() {
        try {
            patientService.deletePatient(1);
            assertNull(patientService.findPatientById(1));
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
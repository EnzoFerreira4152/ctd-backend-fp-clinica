package com.backend.finalProject.service.impl;

import com.backend.finalProject.exceptions.ResourceNotFoundException;
import com.backend.finalProject.model.DentistDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class DentistServiceTest {

    @Autowired
    private DentistService dentistService;

    @BeforeAll
    void setUp() {
        DentistDTO dentist = new DentistDTO();
        dentist.setName("Juan");
        dentist.setLastName("Perez");
        dentist.setProfessionalRegistrationNumber("PR-b3122");

        dentistService.addDentist(dentist);
    }

    @Test
    void addDentist() {
        DentistDTO dentist = new DentistDTO();
        dentist.setName("Fernanda");
        dentist.setLastName("Martinez");
        dentist.setProfessionalRegistrationNumber("PR-a3122");

        DentistDTO response = dentistService.addDentist(dentist);

        assertNotNull(response);
    }

    @Test
    void listAllDentist() {
        Set<DentistDTO> response = dentistService.listAllDentist();
        assertTrue(response.size() > 0);
    }

    @Test
    void findDentistById() {
        try{
            DentistDTO response = dentistService.findDentistById(1);
            assertEquals("Juan", response.getName());
        } catch (ResourceNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void modifyDentist() {
        try {
        DentistDTO dentistWithNewData = dentistService.findDentistById(1);

        //Cambio su matrícula
        dentistWithNewData.setProfessionalRegistrationNumber("PR-c3052");
        DentistDTO response = dentistService.modifyDentist(dentistWithNewData);

        assertEquals("PR-c3052", response.getProfessionalRegistrationNumber());

        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void deleteDentist() {
        try{
            dentistService.deleteDentist(1);
            assertNull(dentistService.findDentistById(1));
        } catch (ResourceNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
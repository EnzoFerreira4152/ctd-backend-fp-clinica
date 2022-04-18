package com.backend.finalProject.controller;

import com.backend.finalProject.model.PatientDTO;
import com.backend.finalProject.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService service;

    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO){
        return ResponseEntity.ok(service.addPatient(patientDTO));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findPatientById(@PathVariable Integer id){
        PatientDTO response = service.findPatientById(id);
        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with id " + id + " not found");
        }

    }

    @GetMapping
    public ResponseEntity<Set<PatientDTO>> listAllPatient(){
        return ResponseEntity.ok(service.listAllPatients());
    }

    @PutMapping
    public ResponseEntity<?> modifyPatient(@RequestBody PatientDTO patientDTO){
        return ResponseEntity.ok(service.modifyPatient(patientDTO));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Integer id){
        if(service.findPatientById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        } else {
            service.deletePatient(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The patient whit id " + id + " was successfully deleted from the database.");
        }
    }

}

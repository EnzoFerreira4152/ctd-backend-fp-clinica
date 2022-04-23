package com.backend.finalProject.controller;

import com.backend.finalProject.exceptions.ResourceNotFoundException;
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

    private PatientService service;

    @Autowired
    public PatientController(PatientService service){
        this.service = service;
    }

    //Get one patient by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findPatientById(@PathVariable Integer id) throws ResourceNotFoundException {
        PatientDTO response = service.findPatientById(id);
        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with id " + id + " not found");
        }

    }

    //Get all patients
    @GetMapping
    public ResponseEntity<Set<PatientDTO>> listAllPatient(){
        return ResponseEntity.ok(service.listAllPatients());
    }

    //Add one patient
    @PostMapping
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO){
        return ResponseEntity.ok(service.addPatient(patientDTO));
    }

    //Edit one patient
    @PutMapping
    public ResponseEntity<?> modifyPatient(@RequestBody PatientDTO patientDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.modifyPatient(patientDTO));
    }

    //Delete on patient by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Integer id) throws ResourceNotFoundException {
        if(service.findPatientById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        } else {
            service.deletePatient(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The patient whit id " + id + " was successfully deleted");
        }
    }

}

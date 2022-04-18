package com.backend.finalProject.controller;

import com.backend.finalProject.model.DentistDTO;
import com.backend.finalProject.service.impl.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/dentists")
public class DentistController {

    @Autowired
    DentistService service;

    @PostMapping
    public ResponseEntity<DentistDTO> addDentist(@RequestBody DentistDTO dentistDTO){
        return ResponseEntity.ok(service.addDentist(dentistDTO));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DentistDTO> findDentistById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findDentistById(id));
    }

    @GetMapping
    public ResponseEntity<Set<DentistDTO>> listAllDentist(){
        return ResponseEntity.ok(service.listAllDentist());
    }

    @PutMapping
    public ResponseEntity<DentistDTO> modifyDentist(@RequestBody DentistDTO dentistDTO){
        return ResponseEntity.ok(service.modifyDentist(dentistDTO));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Integer id){
        if(service.findDentistById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dentist not found");
        } else {
            service.deleteDentist(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The dentist was successfully deleted from the database");
        }
    }

}

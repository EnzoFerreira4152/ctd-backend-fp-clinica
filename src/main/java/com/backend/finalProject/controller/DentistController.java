package com.backend.finalProject.controller;

import com.backend.finalProject.exceptions.ResourceNotFoundException;
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

    private DentistService service;

    @Autowired
    public DentistController(DentistService service){
        this.service = service;
    }

    //Get one dentist by id
    @GetMapping("/{id}")
    public ResponseEntity<DentistDTO> findDentistById(@PathVariable Integer id) throws ResourceNotFoundException{
        return ResponseEntity.ok(service.findDentistById(id));
    }

    //Get all dentists
    @GetMapping
    public ResponseEntity<Set<DentistDTO>> listAllDentist(){
        return ResponseEntity.ok(service.listAllDentist());
    }

    //Add one dentist
    @PostMapping
    public ResponseEntity<DentistDTO> addDentist(@RequestBody DentistDTO dentistDTO){
        return ResponseEntity.ok(service.addDentist(dentistDTO));
    }

    //Edit one dentist
    @PutMapping
    public ResponseEntity<DentistDTO> modifyDentist(@RequestBody DentistDTO dentistDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.modifyDentist(dentistDTO));
    }

    //Delete on dentist by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Integer id) throws ResourceNotFoundException {
        service.deleteDentist(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The dentist was successfully deleted from the database");
    }

}

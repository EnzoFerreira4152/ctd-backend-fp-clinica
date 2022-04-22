package com.backend.finalProject.controller;

import com.backend.finalProject.exceptions.ResourceNotFoundException;
import com.backend.finalProject.model.AppointmentDTO;
import com.backend.finalProject.service.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {


    private AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service){
        this.service = service;
    }

    //Get one apoointment by id
    @GetMapping("/{id}")
    public ResponseEntity<?> findAppointmentById(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        return ResponseEntity.ok(service.findAppointmentById(id));
    }

    //Get all appointments
    @GetMapping
    public ResponseEntity<Set<AppointmentDTO>> listAllAppointments(){
        return ResponseEntity.ok(service.listAllAppointments());
    }

    //Add one appointment
    @PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException{
        return ResponseEntity.ok(service.addAppointment(appointmentDTO));
    }

    //Edit one appointment
    @PutMapping
    public ResponseEntity<?> modifyAppointment(@RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException{
        return ResponseEntity.ok(service.modifyAppointment(appointmentDTO));
    }

    //Delete one appointment by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") Integer id) throws ResourceNotFoundException{
        service.deleteAppointment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The appointment was succesfully deleted");
    }

}

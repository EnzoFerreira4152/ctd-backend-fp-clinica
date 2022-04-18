package com.backend.finalProject.controller;

import com.backend.finalProject.service.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
}

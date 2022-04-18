package com.backend.finalProject.service.impl;

import com.backend.finalProject.model.AppointmentDTO;
import com.backend.finalProject.persistence.repository.IAppointmentRepository;
import com.backend.finalProject.service.IAppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class AppointmentService implements IAppointmentService {

    @Autowired
    IAppointmentRepository repository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public AppointmentDTO addAppointment(AppointmentDTO appointmentDTO) {
        return null;
    }

    @Override
    public Set<AppointmentDTO> listAllAppointment() {
        return null;
    }

    @Override
    public AppointmentDTO findAppointmentById(Integer id) {
        return null;
    }

    @Override
    public AppointmentDTO modifyAppointment(AppointmentDTO appointmentDTO) {
        return null;
    }

    @Override
    public void deleteAppointment(Integer id) {

    }
}

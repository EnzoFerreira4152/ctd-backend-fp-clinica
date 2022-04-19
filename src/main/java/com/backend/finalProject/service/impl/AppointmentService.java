package com.backend.finalProject.service.impl;

import com.backend.finalProject.model.AppointmentDTO;
import com.backend.finalProject.persistence.repository.IAppointmentRepository;
import com.backend.finalProject.service.IAppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public AppointmentService(IAppointmentRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }
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

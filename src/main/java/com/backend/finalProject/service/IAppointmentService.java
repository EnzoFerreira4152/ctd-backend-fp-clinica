package com.backend.finalProject.service;

import com.backend.finalProject.exceptions.ResourceNotFoundException;
import com.backend.finalProject.model.AppointmentDTO;

import java.util.Set;

public interface IAppointmentService {

    AppointmentDTO addAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException;
    Set<AppointmentDTO> listAllAppointment();
    AppointmentDTO findAppointmentById(Integer id) throws ResourceNotFoundException;
    AppointmentDTO modifyAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException;
    void deleteAppointment(Integer id) throws ResourceNotFoundException;

}

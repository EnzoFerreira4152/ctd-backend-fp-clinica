package com.backend.finalProject.service;

import com.backend.finalProject.model.AppointmentDTO;

import java.util.Set;

public interface IAppointmentService {

    AppointmentDTO addAppointment(AppointmentDTO appointmentDTO);
    Set<AppointmentDTO> listAllAppointment();
    AppointmentDTO findAppointmentById(Integer id);
    AppointmentDTO modifyAppointment(AppointmentDTO appointmentDTO);
    void deleteAppointment(Integer id);

}

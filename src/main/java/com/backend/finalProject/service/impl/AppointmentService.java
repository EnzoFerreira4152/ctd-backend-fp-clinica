package com.backend.finalProject.service.impl;

import com.backend.finalProject.model.AppointmentDTO;
import com.backend.finalProject.model.DentistDTO;
import com.backend.finalProject.model.PatientDTO;
import com.backend.finalProject.persistence.entities.Appointment;
import com.backend.finalProject.persistence.entities.Dentist;
import com.backend.finalProject.persistence.entities.Patient;
import com.backend.finalProject.persistence.repository.IAppointmentRepository;
import com.backend.finalProject.persistence.repository.IDentistRepository;
import com.backend.finalProject.persistence.repository.IPatientRepository;
import com.backend.finalProject.service.IAppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository repository;
    private final IPatientRepository p_repository;
    private final IDentistRepository d_repository;
    private final ObjectMapper mapper;

    @Autowired
    public AppointmentService(IAppointmentRepository repository, IPatientRepository p_repository, IDentistRepository d_repository, ObjectMapper mapper){
        this.repository = repository;
        this.p_repository = p_repository;
        this.d_repository = d_repository;
        this.mapper = mapper;
    }

    //Se utiliza tanto para guardar un turno nuevo como para modificarlo
    private AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO){
        Optional<Patient> patient = p_repository.findById(appointmentDTO.getPatient_id());
        Optional<Dentist> dentist = d_repository.findById(appointmentDTO.getDentist_id());

        Appointment appointment = mapper.convertValue(appointmentDTO, Appointment.class);

        if (patient.isPresent() && dentist.isPresent()) {
            appointment.setDate(appointmentDTO.getDate());
            appointment.setDentist(dentist.get());
            appointment.setPatient(patient.get());
        }else{
            //TODO: debe arrojar una excepción
        }

        AppointmentDTO response = mapper.convertValue(repository.save(appointment), AppointmentDTO.class);
        response.setDentist_id(appointment.getDentist().getId());
        response.setPatient_id(appointment.getPatient().getId());

        return response;
    }

    @Override
    public AppointmentDTO addAppointment(AppointmentDTO appointmentDTO) {
        return saveAppointment(appointmentDTO);
    }

    @Override
    public Set<AppointmentDTO> listAllAppointment() {
        Set<AppointmentDTO> list = new HashSet<>();
        for (Appointment appointment : repository.findAll()) {
            AppointmentDTO appointmentDTO = mapper.convertValue(appointment, AppointmentDTO.class);
            appointmentDTO.setDentist_id(appointment.getDentist().getId());
            appointmentDTO.setPatient_id(appointment.getPatient().getId());
            list.add(appointmentDTO);
        }
        return list;
    }

    @Override
    public AppointmentDTO findAppointmentById(Integer id) {
        return mapper.convertValue(repository.findById(id), AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO modifyAppointment(AppointmentDTO appointmentDTO) {
        Optional<Appointment> appointment = repository.findById(appointmentDTO.getId());

        if (appointment.isPresent()) {
            Appointment prevAppointmentData = appointment.get();

            if (appointmentDTO.getDate() == null) {
                appointmentDTO.setDate(prevAppointmentData.getDate());

            }if (appointmentDTO.getDentist_id() == null) {
                appointmentDTO.setDentist_id(prevAppointmentData.getDentist().getId());

            }if (appointmentDTO.getPatient_id() == null) {
                appointmentDTO.setPatient_id(prevAppointmentData.getPatient().getId());
            }

        }else{
            //TODO: debe retornar una excepción
        }


        return saveAppointment(appointmentDTO);
    }

    @Override
    public void deleteAppointment(Integer id) {
        repository.deleteById(id);
    }

}

package com.backend.finalProject.service.impl;

import com.backend.finalProject.exceptions.BadRequestException;
import com.backend.finalProject.exceptions.ResourceNotFoundException;
import com.backend.finalProject.model.AppointmentDTO;
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

/**
 * Realiza todas las operaciones basicas sobre la base de datos para administrar y obtener los datos de cada turno.
 * Implementa la interfaz IAppointmentService
 */
@Service
public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository repository;
    private final IDentistRepository d_repository;
    private final IPatientRepository p_repository;
    private final ObjectMapper mapper;

    @Autowired
    public AppointmentService(IAppointmentRepository repository, IPatientRepository p_repository, IDentistRepository d_repository, ObjectMapper mapper){
        this.repository = repository;
        this.p_repository = p_repository;
        this.d_repository = d_repository;
        this.mapper = mapper;
    }

    /**
     * Guarda un nuevo turno o un turno que necesita ser modificado.
     * Chequea que tanto el paciente y el odontólogo que se van a insertar en el turno existan, si no arroja una excepción.
     * Guarda el turno creado en las colecciones de turnos del paciente correspondiente y del odontologo correspondiente.
     * @param appointmentDTO El DTO del turno con todos los datos necesarios. No deben ser nulos.
     * @return AppointmentDTO
     */
    private AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException{
        Appointment appointment = mapper.convertValue(appointmentDTO, Appointment.class);

        Optional<Dentist> dentist = d_repository.findById(appointmentDTO.getDentistId());
        Optional<Patient> patient = p_repository.findById(appointmentDTO.getPatientId());
        Dentist d_Obj = null;
        Patient p_Obj = null;

        if (patient.isPresent() && dentist.isPresent()) {
            d_Obj = dentist.get();
            p_Obj = patient.get();
            appointment.setDentist(d_Obj);
            appointment.setPatient(p_Obj);
        }else {
            throw new ResourceNotFoundException("The dentist or patient you are trying to add an appointment for does not exist. Check if any of them are registered in the system before trying again.");
        }

        Appointment savedAppointment = repository.save(appointment);

        d_Obj.getAppointments().add(savedAppointment);
        p_Obj.getAppointments().add(savedAppointment);
        d_repository.save(d_Obj);
        p_repository.save(p_Obj);

        AppointmentDTO response = mapper.convertValue(savedAppointment, AppointmentDTO.class);

        response.setDentistId(appointment.getDentist().getId());
        response.setPatientId(appointment.getPatient().getId());

        return response;
    }

    /**
     * Guarda un nuevo turno. Ningúno de sus campos debe estar vacío o nulo, de lo contrario arroja un excepción.
     * @param appointmentDTO El DTO del turno con todos los datos necesarios, menos el ID, este se genera y asigna automáticamente. No deben ser nulos los datos.
     * @return AppointmentDTO
     */
    @Override
    public AppointmentDTO addAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException{
        if (appointmentDTO.getDate() == null || appointmentDTO.getDentistId() == null || appointmentDTO.getPatientId() == null) {
            throw new BadRequestException("There are some fields whith null vaule. Please check and complete them.");
        }
        return saveAppointment(appointmentDTO);
    }

    /**
     * Lista todos los turnos existentes en la base de datos.
     * @return Devuelve una colección de DTO de turnos. Solo están presentes, la fecha del turno el ID del paciente y el ID del odontólogo
     */
    @Override
    public Set<AppointmentDTO> listAllAppointments() {
        Set<AppointmentDTO> list = new HashSet<>();

        for (Appointment appointment : repository.findAll()) {
            AppointmentDTO a_dto = mapper.convertValue(appointment, AppointmentDTO.class);

            a_dto.setDentistId(appointment.getDentist().getId());
            a_dto.setPatientId(appointment.getPatient().getId());

            list.add(a_dto);
        }
        return list;
    }

    /**
     * Busca en la base de datos el turno coincidente con el ID entregado.
     * @param id ID con el que se quiere buscar el turno
     * @return AppointmentDTO
     */
    @Override
    public AppointmentDTO findAppointmentById(Integer id) throws ResourceNotFoundException{
        AppointmentDTO response = mapper.convertValue(repository.findById(id), AppointmentDTO.class);
        if (response == null){
            throw new ResourceNotFoundException("The appointment whith id " + id + " does not exist.");
        }
        return response;
    }

    /**
     * Modifica los datos de un turno que ya existe en la base de datos.
     * Corrobora que el turno exista antes de intentar modificarlo. Si no existe arroja una excepción.
     * Si algúno de los campos llegan nulos, los completa autamáticamente con los datos del turno guardado previamente, dandose por entendido que los datos nulos no tenian intención de ser modificados.
     * @param appointmentDTO El DTO del turno con todos los datos que se requieren alterar y el ID del turno a modificar. Pueden ser nulos aquellos datos que no se deseen cambiar.
     * @return AppointmentDTO
     */
    @Override
    public AppointmentDTO modifyAppointment(AppointmentDTO appointmentDTO) throws ResourceNotFoundException{
        Optional<Appointment> appointment = repository.findById(appointmentDTO.getId());

        if (appointment.isPresent()) {
            Appointment prevAppointmentData = appointment.get();

            if (appointmentDTO.getDate() == null) {
                appointmentDTO.setDate(prevAppointmentData.getDate());
            }
            if (appointmentDTO.getDentistId() == null) {
                appointmentDTO.setDentistId(prevAppointmentData.getDentist().getId());
            }
            if (appointmentDTO.getPatientId() == null) {
                appointmentDTO.setPatientId(prevAppointmentData.getPatient().getId());
            }

        }else{
            throw new ResourceNotFoundException("The appointment with id "+ appointmentDTO.getId() +" you are trying to modify does not exist.");
        }

        return saveAppointment(appointmentDTO);
    }

    /**
     * Borra de la base de datos un turno coincidente con el ID entregado.
     * Si el turno a borrar no existe arroja una excepción.
     * @param id ID con el que se desea indicar que turno borrar.
     */
    @Override
    public void deleteAppointment(Integer id) throws ResourceNotFoundException{
        if(repository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("The appointment whith id " + id + "can not be deleted because does not exist.");
        }
        repository.deleteById(id);
    }

}

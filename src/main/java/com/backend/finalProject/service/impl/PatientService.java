package com.backend.finalProject.service.impl;

import com.backend.finalProject.exceptions.ResourceNotFoundException;
import com.backend.finalProject.model.AddressDTO;
import com.backend.finalProject.model.PatientDTO;
import com.backend.finalProject.persistence.entities.Patient;
import com.backend.finalProject.persistence.repository.IPatientRepository;
import com.backend.finalProject.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Realiza todas las operaciones basicas sobre la base de datos para administrar y obtener los datos de cada paciente.
 * Implementa la interfaz IPatientService
 */
@Service
public class PatientService implements IPatientService {
    private final IPatientRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public PatientService(IPatientRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Este método se utiliza tanto para guardar un paciente nuevo como para modificarlo
     * @param patientDTO El DTO del paciente con todos los datos a guardar o modificar.
     * @return PatientDTO
     */
    private PatientDTO savePatient(PatientDTO patientDTO){
        Patient patient = mapper.convertValue(patientDTO, Patient.class);
        return mapper.convertValue(repository.save(patient), PatientDTO.class);
    }

    /**
     * Guarda un nuevo paciente en la base de datos. No debe tener datos nulos de lo contrario arroja una excepción.
     * @param patientDTO El DTO del paciente con todos los datos necesarios. No deben ser nulos.
     * @return PatientDTO
     */
    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {
        return savePatient(patientDTO);
    }

    /**
     * Lista todos los pacientes existentes en la base de datos.
     * @return Una colección de DTO de pacientes.
     */
    @Override
    public Set<PatientDTO> listAllPatients() {
        Set<PatientDTO> list = new HashSet<>();
        for(Patient patient : repository.findAll()){
            list.add(mapper.convertValue(patient, PatientDTO.class));
        }
        return list;
    }

    /**
     * Busca en la base de datos el paciente coincidente con el ID entregado.
     * @param id ID con el que se quiere buscar el paciente.
     * @return PatientDTO
     */
    @Override
    public PatientDTO findPatientById(Integer id) {
        return mapper.convertValue(repository.findById(id), PatientDTO.class);
    }

    /**
     * Modifica los datos de un paciente que ya exista en la base de datos.
     * Corrobora que el paciente exista antes de modificarlo. Si no existe arroja una excepción.
     * Si alguno de los datos que llegan son nulos, los completa automaticamente con los datos del paciente guardado previamente, dandose por entendido que no los datos nulos no tenian intención de ser modificados.
     * @param patientDTO El DTO de paciente con todos los datos que se requieran alterar. Puede tener campos nulos.
     * @return PatientDTO
     */
    @Override
    public PatientDTO modifyPatient(PatientDTO patientDTO) throws ResourceNotFoundException {
        Optional<Patient> patient = repository.findById(patientDTO.getId());

        if (patient.isPresent()) {
            Patient prevPatientData = patient.get();

            if (patientDTO.getAddress() == null) {
                AddressDTO address = mapper.convertValue(prevPatientData.getAddress(), AddressDTO.class);
                patientDTO.setAddress(address);
            }
            if (patientDTO.getDischargeDate() == null) {
                patientDTO.setDischargeDate(prevPatientData.getDischargeDate());
            }
            if (patientDTO.getDNI() == null) {
                patientDTO.setDNI(prevPatientData.getDni());
            }

        }else{
            throw new ResourceNotFoundException("The patient with id "+ patientDTO.getId() +" you are trying to modify does not exist.");
        }

        return savePatient(patientDTO);
    }

    /**
     * Borra de la base de datos un paciente coincidente con el ID entregado.
     * @param id ID con el que se desea indicar que paciente borrar.
     */
    @Override
    public void deletePatient(Integer id) {
        repository.deleteById(id);
    }

}

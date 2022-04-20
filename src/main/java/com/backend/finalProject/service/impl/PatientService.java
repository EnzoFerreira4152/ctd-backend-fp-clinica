package com.backend.finalProject.service.impl;

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

@Service
public class PatientService implements IPatientService {

    private final IPatientRepository repository;
    private final ObjectMapper mapper;

    @Autowired
    public PatientService(IPatientRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    //Este método se utiliza tanto para guardar un paciente nuevo como para modificarlo
    private PatientDTO savePatient(PatientDTO patientDTO){
        Patient patient = mapper.convertValue(patientDTO, Patient.class);
        return mapper.convertValue(repository.save(patient), PatientDTO.class);
    }

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {
        return savePatient(patientDTO);
    }

    @Override
    public Set<PatientDTO> listAllPatients() {
        Set<PatientDTO> list = new HashSet<>();
        for(Patient patient : repository.findAll()){
            list.add(mapper.convertValue(patient, PatientDTO.class));
        }
        return list;
    }

    @Override
    public PatientDTO findPatientById(Integer id) {
        return mapper.convertValue(repository.findById(id), PatientDTO.class);
    }

    @Override
    public PatientDTO modifyPatient(PatientDTO patientDTO) {
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
            //TODO: Debe retornar una excepción
        }

        return savePatient(patientDTO);
    }

    @Override
    public void deletePatient(Integer id) {
        repository.deleteById(id);
    }

}

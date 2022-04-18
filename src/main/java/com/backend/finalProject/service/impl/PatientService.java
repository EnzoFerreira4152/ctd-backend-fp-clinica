package com.backend.finalProject.service.impl;


import com.backend.finalProject.model.PatientDTO;
import com.backend.finalProject.persistence.entities.Patient;
import com.backend.finalProject.persistence.repository.IPatientRepository;
import com.backend.finalProject.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PatientService implements IPatientService {

    @Autowired
    IPatientRepository patientRepository;
    @Autowired
    ObjectMapper mapper;

    private PatientDTO savePatient(PatientDTO patientDTO){
        Patient patientToSave = mapper.convertValue(patientDTO, Patient.class);
        return mapper.convertValue(patientRepository.save(patientToSave), PatientDTO.class);
    }

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {
        return savePatient(patientDTO);
    }

    @Override
    public Set<PatientDTO> listAllPatients() {
        Set<PatientDTO> list = new HashSet<>();
        for(Patient patient : patientRepository.findAll()){
            list.add(mapper.convertValue(patient, PatientDTO.class));
        }
        return list;
    }

    @Override
    public PatientDTO findPatientById(Integer id) {
        return mapper.convertValue(patientRepository.findById(id), PatientDTO.class);
    }

    @Override
    public PatientDTO modifyPatient(PatientDTO patientDTO) {
        return savePatient(patientDTO);
    }

    @Override
    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }

}

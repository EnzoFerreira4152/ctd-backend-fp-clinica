package com.backend.finalProject.service.impl;

import com.backend.finalProject.model.DentistDTO;
import com.backend.finalProject.persistence.entities.Dentist;
import com.backend.finalProject.persistence.repository.IDentistRepository;
import com.backend.finalProject.service.IDentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DentistService implements IDentistService {

    @Autowired
    IDentistRepository repository;
    @Autowired
    ObjectMapper mapper;

    private DentistDTO saveDentist(DentistDTO dentistDTO){
        Dentist dentist = mapper.convertValue(dentistDTO, Dentist.class);
        return mapper.convertValue(repository.save(dentist), DentistDTO.class);
    }

    @Override
    public DentistDTO addDentist(DentistDTO dentistDTO){
        return saveDentist(dentistDTO);
    }

    @Override
    public Set<DentistDTO> listAllDentist(){
        Set<DentistDTO> list = new HashSet<>();
        for(Dentist dentist : repository.findAll()){
            list.add(mapper.convertValue(dentist, DentistDTO.class));
        }
        return list;
    }

    @Override
    public DentistDTO findDentistById(Integer id){
        return mapper.convertValue(repository.findById(id), DentistDTO.class);
    }

    @Override
    public DentistDTO modifyDentist(DentistDTO dentistDTO){
        return saveDentist(dentistDTO);
    }

    @Override
    public void deleteDentist(Integer id){
        repository.deleteById(id);
    }

}

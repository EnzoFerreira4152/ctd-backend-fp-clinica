package com.backend.finalProject.service;

import com.backend.finalProject.exceptions.ResourceNotFoundException;
import com.backend.finalProject.model.DentistDTO;
import java.util.Set;

public interface IDentistService {

    DentistDTO addDentist(DentistDTO dentistDTO);
    Set<DentistDTO> listAllDentist();
    DentistDTO findDentistById(Integer id) throws ResourceNotFoundException;
    DentistDTO modifyDentist(DentistDTO dentistDTO) throws ResourceNotFoundException;
    void deleteDentist(Integer id) throws ResourceNotFoundException;

}

package com.backend.finalProject.service;

import com.backend.finalProject.model.DentistDTO;
import java.util.Set;

public interface IDentistService {

    DentistDTO addDentist(DentistDTO dentistDTO);
    Set<DentistDTO> listAllDentist();
    DentistDTO findDentistById(Integer id);
    DentistDTO modifyDentist(DentistDTO dentistDTO);
    void deleteDentist(Integer id);

}

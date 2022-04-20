package com.backend.finalProject.service.impl;

import com.backend.finalProject.model.DentistDTO;
import com.backend.finalProject.persistence.entities.Dentist;
import com.backend.finalProject.persistence.repository.IDentistRepository;
import com.backend.finalProject.service.IDentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Realiza todas las operaciones basicas sobre la base de datos para administrar y obtener los datos de cada odontólogo.
 * Implementa la interfaz IDentistService
 */
@Service
public class DentistService implements IDentistService {

    private final IDentistRepository repository;

    private final ObjectMapper mapper;

    @Autowired
    public DentistService(IDentistRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Guarda un nuevo odontólogo o un odontólogo que necesita ser modificado.
     * @param dentistDTO El DTO del odontólogo con todos los datos a guardar o modificar.
     * @return DentistDTO
     */
    private DentistDTO saveDentist(DentistDTO dentistDTO){
        Dentist dentist = mapper.convertValue(dentistDTO, Dentist.class);
        return mapper.convertValue(repository.save(dentist), DentistDTO.class);
    }

    /**
     * Guarda un nuevo odontólogo en la base de datos. No debe tener datos nulos de lo contrario retorna una excepción.
     * @param dentistDTO El DTO del odontólogo con todos los datos necesarios. No deben ser nulos.
     * @return DentistDTO
     */
    @Override
    public DentistDTO addDentist(DentistDTO dentistDTO){
        DentistDTO response = new DentistDTO();
        if(dentistDTO.getName() != null && dentistDTO.getLastName() != null && dentistDTO.getProfessionalRegistrationNumber() != null){
            response = saveDentist(dentistDTO);
        }else {
            //TODO: debe retornar un excepción
        }
        return response;
    }

    /**
     * Lista todos los odontólogos existentes en la base de datos.
     * Devuelve una colección de DTOs de odontólogos.
     * @return Set
     */
    @Override
    public Set<DentistDTO> listAllDentist(){
        Set<DentistDTO> list = new HashSet<>();
        for(Dentist dentist : repository.findAll()){
            list.add(mapper.convertValue(dentist, DentistDTO.class));
        }
        return list;
    }

    /**
     * Busca en la base de datos el odontólogo coincidente con el ID entregado.
     * @param id ID con el que se quiere buscar el odontólogo.
     * @return DentistDTO
     */
    @Override
    public DentistDTO findDentistById(Integer id){
        return mapper.convertValue(repository.findById(id), DentistDTO.class);
    }

    /**
     * Modifica los datos de un odontólogo que ya exista en la base de datos.
     * Corrobora que el odontólogo exista antes de modificarlo. Si no existe retorna una excepción.
     * Si alguno de los datos que llegan es nulo, los completa automaticamente con los datos del odontólogo guardado previamente, dandose por entendido que no los datos nulos no tenian intención de ser modificados.
     * @param dentistDTO El DTO de odontólogo con todos los datos que se requieran alterar. Puede tener campos nulos.
     * @return DentistDTO
     */
    @Override
    public DentistDTO modifyDentist(DentistDTO dentistDTO){
        Optional<Dentist> dentist = repository.findById(dentistDTO.getId());

        if(dentist.isPresent()){
            Dentist prevDentistData = dentist.get();

            if (dentistDTO.getName() == null) {
                dentistDTO.setName(prevDentistData.getName());
            }
            if (dentistDTO.getLastName() == null){
                dentistDTO.setLastName(prevDentistData.getLastName());
            }
            if (dentistDTO.getProfessionalRegistrationNumber() == null){
                dentistDTO.setProfessionalRegistrationNumber(prevDentistData.getProfessionalRegistrationNumber());
            }

        }else {
            //TODO: debe retornar una excepción
        }

        return saveDentist(dentistDTO);
    }

    /**
     * Borra de la base de datos un odontólogo coincidente con el ID entregado.
     * @param id ID con el que se desea indicar que odontólogo borrar.
     */
    @Override
    public void deleteDentist(Integer id){
        repository.deleteById(id);
    }

}

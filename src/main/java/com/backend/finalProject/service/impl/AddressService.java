package com.backend.finalProject.service.impl;

import com.backend.finalProject.model.AddressDTO;
import com.backend.finalProject.persistence.entities.Address;
import com.backend.finalProject.persistence.repository.IAddressRepository;
import com.backend.finalProject.service.IAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AddressService implements IAddressService {

    @Autowired
    IAddressRepository repository;
    @Autowired
    ObjectMapper mapper;

    private AddressDTO saveAddress(AddressDTO addressDTO){
        Address address = mapper.convertValue(addressDTO, Address.class);
        return mapper.convertValue(repository.save(address), AddressDTO.class);
    }

    @Override
    public AddressDTO addAddress(AddressDTO addressDTO){
        return saveAddress(addressDTO);
    }

    @Override
    public Set<AddressDTO> listAllAddress(){
        Set<AddressDTO> list = new HashSet<>();
        for(Address address : repository.findAll()){
            list.add(mapper.convertValue(address, AddressDTO.class));
        }
        return list;
    }

    @Override
    public AddressDTO findAddressById(Integer id){
        return mapper.convertValue(repository.findById(id), AddressDTO.class);
    }

    @Override
    public AddressDTO modifyAddress(AddressDTO addressDTO){
        return saveAddress(addressDTO);
    }

    @Override
    public void deleteAddress(Integer id){
        repository.deleteById(id);
    }

}

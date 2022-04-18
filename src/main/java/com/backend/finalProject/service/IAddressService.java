package com.backend.finalProject.service;

import com.backend.finalProject.model.AddressDTO;

import java.util.Set;

public interface IAddressService {

    AddressDTO addAddress(AddressDTO addressDTO);
    Set<AddressDTO> listAllAddress();
    AddressDTO findAddressById(Integer id);
    AddressDTO modifyAddress(AddressDTO addressDTO);
    void deleteAddress(Integer id);

}

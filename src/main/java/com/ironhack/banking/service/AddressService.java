package com.ironhack.banking.service;

import com.ironhack.banking.model.tools.Address;
import com.ironhack.banking.repository.AddressRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    public Address saveNew(Address address) {
        Address add = new Address(address.getCity(), address.getStreet(), address.getStreetNumber(), address.getFloorNumber(), address.getPostalCode());
        LOGGER.info("New Address created");
        return addressRepository.save(add);

    }
}

package com.ironhack.banking.controller;

import com.ironhack.banking.model.tools.Address;
import com.ironhack.banking.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/address")
    public Address saveNew(@RequestBody @Valid Address address) {
        return addressService.saveNew(address);
    }
}

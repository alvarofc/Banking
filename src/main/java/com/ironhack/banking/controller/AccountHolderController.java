package com.ironhack.banking.controller;

import com.ironhack.banking.model.users.AccountHolder;
import com.ironhack.banking.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;
    @PostMapping("/new-account-holder")
    public AccountHolder newAccountHolder(@RequestBody @Valid AccountHolder accountHolder){
        return accountHolderService.save(accountHolder);
    }
}

package com.ironhack.banking.service;

import com.ironhack.banking.model.users.AccountHolder;
import com.ironhack.banking.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public AccountHolder findById(Integer id) throws Exception {
        return accountHolderRepository.findById(id).orElseThrow(()-> new Exception("No account holder with that id"));
    }
    public AccountHolder save(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }
}

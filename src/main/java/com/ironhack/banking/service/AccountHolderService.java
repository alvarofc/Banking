package com.ironhack.banking.service;

import com.ironhack.banking.model.users.AccountHolder;
import com.ironhack.banking.repository.AccountHolderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    public AccountHolder findById(Integer id) throws Exception {
        LOGGER.info("Looked for AccountHolder" + id);
        return accountHolderRepository.findById(id).orElseThrow(()-> new Exception("No account holder with that id"));
    }
    public AccountHolder save(AccountHolder accountHolder) {
        LOGGER.info("New AccountHolder created");
        return accountHolderRepository.save(accountHolder);
    }
}

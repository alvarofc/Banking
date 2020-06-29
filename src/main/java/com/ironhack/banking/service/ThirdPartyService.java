package com.ironhack.banking.service;

import com.ironhack.banking.model.users.ThirdParty;
import com.ironhack.banking.repository.ThirdPartyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ThirdPartyService {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    private static final Logger LOGGER = LogManager.getLogger(AddressService.class);

    public ThirdParty saveNew(Integer key, String name){
        HashMap<Integer, String> thirdParty = new HashMap();
        thirdParty.put(key, name);
        ThirdParty newThirdParty = new ThirdParty(thirdParty);
        LOGGER.info("New Third Party");
        return thirdPartyRepository.save(newThirdParty);
    }
}

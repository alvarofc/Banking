package com.ironhack.banking.controller;

import com.ironhack.banking.model.users.ThirdParty;
import com.ironhack.banking.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyController {
    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/third-party")
    public ThirdParty saveNew(@RequestParam Integer key, @RequestParam String name) {
        return thirdPartyService.saveNew(key, name);
    }
}

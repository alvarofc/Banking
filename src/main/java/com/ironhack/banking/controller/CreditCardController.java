package com.ironhack.banking.controller;

import com.ironhack.banking.model.accounts.CreditCard;
import com.ironhack.banking.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @PostMapping("/creditcard")
    public CreditCard save(@RequestBody @Valid CreditCard creditCard) throws Exception {
        return creditCardService.save(creditCard);
    }
}

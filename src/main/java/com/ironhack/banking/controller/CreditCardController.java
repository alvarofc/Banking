package com.ironhack.banking.controller;

import com.ironhack.banking.model.accounts.CreditCard;
import com.ironhack.banking.model.transactions.CreditTransaction;
import com.ironhack.banking.repository.CreditTransactionRepository;
import com.ironhack.banking.service.CreditCardService;
import com.ironhack.banking.service.CreditTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CreditCardController {

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    CreditTransactionService creditTransactionService;

    @PostMapping("/creditcard")
    public CreditCard save(@RequestBody @Valid CreditCard creditCard) throws Exception {
        return creditCardService.saveNew(creditCard);
    }

    @PatchMapping("/creditcard/withdraw/{id}")
    public CreditTransaction withdraw(@PathVariable(name = "id") Integer id, @RequestParam String amount) throws Exception {
        return creditTransactionService.withdraw(id, amount);
    }

    @PatchMapping("/creditcard/deposit/{id}")
    public CreditTransaction deposit(@PathVariable(name = "id") Integer id, @RequestParam String amount) throws Exception {
        return creditTransactionService.deposit(id, amount);
    }
}

package com.ironhack.banking.controller;

import com.ironhack.banking.model.accounts.Account;
import com.ironhack.banking.model.accounts.Checking;

import com.ironhack.banking.model.accounts.Savings;
import com.ironhack.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> findAccounts() throws Exception {
        return accountService.findAll();
    }

    @GetMapping("/account/{id}")
    public Account findAccount(@PathVariable(name = "id") Integer id) throws Exception {
        return accountService.findById(id);
    }

    @PostMapping("/account")
    public Account newAccount(@RequestBody @Valid Checking account) throws Exception {
        return accountService.saveNew(account);
    }
    @PostMapping("/savings")
    public Account newSavings(@RequestBody @Valid Savings savings) throws Exception {
        Savings newSavings = new Savings(savings.getPrimaryOwner(), savings.getSecondaryOwner(), savings.getBalance().getAmount().toString());
        return accountService.save(newSavings);
    }
}

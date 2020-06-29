package com.ironhack.banking.controller;

import com.ironhack.banking.model.tools.Money;
import com.ironhack.banking.model.transactions.Transaction;
import com.ironhack.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {


    @Autowired
    TransactionService transactionService;


    @GetMapping("/all-transactions")
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @PostMapping("/transfer")
    public Transaction makeTransfer(@RequestBody @Valid Transaction transaction) throws Exception {
        return transactionService.makeTransfer(transaction);
    }

    @GetMapping("/transaction/{id}")
    public Transaction findById(@PathVariable(value = "id") Integer id) throws Exception {
        return transactionService.findById(id);
    }
    @PatchMapping("/withdraw/{id}")
    public Transaction withdraw(@PathVariable(name = "id") Integer id, @RequestParam String amount) throws Exception {
        return transactionService.withdraw(id, amount);
    }

    @PatchMapping("/deposit/{id}")
    public Transaction deposit(@PathVariable(name = "id") Integer id, @RequestParam String amount) throws Exception {
        return transactionService. deposit(id, amount);
    }

}

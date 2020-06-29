package com.ironhack.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.banking.model.accounts.Checking;
import com.ironhack.banking.model.enums.Role;
import com.ironhack.banking.model.tools.Address;
import com.ironhack.banking.model.transactions.Transaction;
import com.ironhack.banking.model.users.AccountHolder;
import com.ironhack.banking.model.users.User;
import com.ironhack.banking.repository.AccountHolderRepository;
import com.ironhack.banking.repository.AccountRepository;
import com.ironhack.banking.repository.AddressRepository;
import com.ironhack.banking.repository.TransactionRepository;
import com.ironhack.banking.security.CustomSecurityUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class TransactionControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    CustomSecurityUser admin;

    @Autowired
    TransactionRepository repository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired //Contexto para recibir peticiones HTTP
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        Address add1 = new Address("Palma", "Jose Maria", 3,4, 7003);
        addressRepository.save(add1);
        AccountHolder ah1 = new AccountHolder("Paco", new Date(), add1);
        accountHolderRepository.save(ah1);
        Checking acc1 = new Checking("2000", ah1);
        Checking acc2 = new Checking("3000", ah1);
        accountRepository.saveAll(Stream.of(acc1, acc2).collect(Collectors.toList()));
        Transaction tr1 = new Transaction(acc1, acc2, "200");
        repository.save(tr1);
        admin = new CustomSecurityUser(new User("test", "test", Role.ADMIN));

    }

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
        accountHolderRepository.deleteAll();
        accountRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/all-transactions").with(user(admin))).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[4].amount.amount").value("200.0"));
    }

    @Test
    void makeTransfer() {
    }

    @Test
    void findById() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void deposit() {
    }
}
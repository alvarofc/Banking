package com.ironhack.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.banking.model.accounts.Checking;
import com.ironhack.banking.model.transactions.Transaction;
import com.ironhack.banking.model.users.AccountHolder;
import com.ironhack.banking.repository.TransactionRepository;
import com.ironhack.banking.security.CustomSecurityUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
class TransactionControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    CustomSecurityUser admin;

    @Autowired
    TransactionRepository repository;

    @Autowired //Contexto para recibir peticiones HTTP
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        AccountHolder ah1 = new AccountHolder();
        Checking acc1 = new Checking()
        Transaction tr1 = Transaction()

    }

    @Test
    void findAll() {
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
package com.ironhack.banking.controller;

import com.ironhack.banking.model.users.User;
import com.ironhack.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }


    @GetMapping("/salesperson")
    @ResponseStatus(HttpStatus.OK)
    public List<User> viewAllSalesPerson() {
        return userService.viewAllSalesPerson();
    }
}

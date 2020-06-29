package com.ironhack.banking.service;

import com.ironhack.banking.model.users.User;
import com.ironhack.banking.repository.UserRepository;
import com.ironhack.banking.security.CustomSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class UserService implements UserDetailsService, Serializable {
    private static final long serialVersionUID = 2L;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        return new CustomSecurityUser(user);
    }

    @Transactional
    public User createUser(User user) throws Exception {
        if (userRepository.findAllByUsername(user.getUsername()).isPresent())
            throw new Exception("User already exists");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> viewAllSalesPerson() {
        return userRepository.findAll();
    }
}

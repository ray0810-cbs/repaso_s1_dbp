package com.example.repasito1.service;

import com.example.repasito1.domain.User;
import com.example.repasito1.infrastructure.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository UserRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.UserRepository = UserRepository;
    }
    
    // === Functions of service ===
}

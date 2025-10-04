package com.example.repasito1.service;

import com.example.repasito1.domain.User;
import com.example.repasito1.infrastructure.UserRepository;

import com.example.repasito1.infrastructure.petRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final petRepository petRepository;

    @Autowired
    public UserService(UserRepository userRepository, petRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    private Long getCurrentUserId() {
        // En un sistema real, esto vendría de la autenticación
        // Por ahora, devolvemos un ID fijo para pruebas
        return 1L;
    }

}

package com.example.repasito1.service;

import com.example.repasito1.domain.Interaction;
import com.example.repasito1.infrastructure.InteractionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionService {
    private final InteractionRepository InteractionRepository;

    @Autowired
    public InteractionService(InteractionRepository interactionRepository) {
        this.InteractionRepository = InteractionRepository;
    }
    
    // === Functions of service ===
}

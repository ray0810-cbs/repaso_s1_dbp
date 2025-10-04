package com.example.repasito1.service;

import com.example.repasito1.domain.AIGeneration;
import com.example.repasito1.infrastructure.AIGenerationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIGenerationService {
    private final AIGenerationRepository AIGenerationRepository;

    @Autowired
    public AIGenerationService(AIGenerationRepository aigenerationRepository) {
        this.AIGenerationRepository = AIGenerationRepository;
    }
    
    // === Functions of service ===
}

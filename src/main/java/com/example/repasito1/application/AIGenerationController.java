package com.example.repasito1.application;

import com.example.repasito1.domain.AIGeneration;
import com.example.repasito1.service.AIGenerationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aigeneration")
public class AIGenerationController {
    private final AIGenerationService AIGenerationService;

    @Autowired
    public AIGenerationController(AIGenerationService aigenerationService) {
        this.AIGenerationService = AIGenerationService;
    }

    // === Endpoints ===
}

package com.example.repasito1.application;

import com.example.repasito1.domain.Interaction;
import com.example.repasito1.service.InteractionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interaction")
public class InteractionController {
    private final InteractionService InteractionService;

    @Autowired
    public InteractionController(InteractionService InteractionService) {
        this.InteractionService = InteractionService;
    }

    // === Endpoints ===
}

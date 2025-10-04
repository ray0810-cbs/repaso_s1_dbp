package com.example.repasito1.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.repasito1.domain.AIGeneration;

public interface AIGenerationRepository extends JpaRepository<AIGeneration, Long> {}

package com.example.repasito1.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.repasito1.domain.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {}

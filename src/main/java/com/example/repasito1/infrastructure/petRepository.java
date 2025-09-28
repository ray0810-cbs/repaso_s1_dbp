package com.example.repasito1.infrastructure;

import com.example.repasito1.domain.pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface petRepository extends JpaRepository<pet,Long> {
}

package com.example.repasito1.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.repasito1.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {}

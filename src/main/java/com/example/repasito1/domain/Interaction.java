package com.example.repasito1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "interaction")
@Getter
@Setter
@NoArgsConstructor
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Integer value;
    private String description;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name="pet_id")
    private pet pet;
    // === Attributes and Constructor === 
}

package com.example.repasito1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pet")
@Getter
@Setter
@NoArgsConstructor //Constructor por default
public class pet {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // Atributos y constructores adicionales

    private String name;
    private String description;
}

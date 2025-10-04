package com.example.repasito1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String type;
    private String imageUrl;

    // Estados de la mascota
    private Integer hunger = 100; // 0-100
    private Integer happiness = 100; // 0-100
    private Integer health = 100; // 0-100
    private Integer energy = 100; // 0-100

    private LocalDateTime lastInteraction; // Último momento de interacción
    private LocalDateTime createdAt; // Fecha de creación/adopción

    //Muchas mascotas para un mismo usuario
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    //Muchas interacciones para una mascota
    //El cascade hace que todas las operaciones sobre el pet se aplicaran tambien a interactions
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<Interaction> interactions= new ArrayList<>();
}

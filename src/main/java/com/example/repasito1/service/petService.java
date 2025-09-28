package com.example.repasito1.service;


import com.example.repasito1.domain.pet;
import com.example.repasito1.infrastructure.petRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class petService {
    private final petRepository petRepository;

    @Autowired
    public petService(petRepository petRepository) { this.petRepository = petRepository; }

    //Funciones

    //Con esta funcion creas (o actualizas) una mascota en la base de datos (petrepository)
    //petRepository es tu repositorio (seguramente extiende de JpaRepository o CrudRepository).
    //save() es un metodo de Spring Data JPA que guarda el objeto en la base de datos.
    //Si newPet no tiene ID, lo inserta como nuevo.
    //Si newPet ya tiene un ID existente , lo actualiza.
    public pet createPet(pet newPet) {
        return petRepository.save(newPet);
    }

    public List<pet> getAllPets() {
        return petRepository.findAll();
    }




}

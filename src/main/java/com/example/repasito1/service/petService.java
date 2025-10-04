package com.example.repasito1.service;


import com.example.repasito1.domain.User;
import com.example.repasito1.domain.pet;
import com.example.repasito1.dto.Pet.PetCreateDTO;
import com.example.repasito1.dto.Pet.PetResponseDTO;
import com.example.repasito1.dto.User.UserSimpleDTO;
import com.example.repasito1.infrastructure.UserRepository;
import com.example.repasito1.infrastructure.petRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class petService {
    private final petRepository petRepository;
    private final UserRepository userRepository;

    @Autowired
    public petService(petRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    //Funciones

    //Con esta funcion creas (o actualizas) una mascota en la base de datos (petrepository)
    //petRepository es tu repositorio (seguramente extiende de JpaRepository o CrudRepository).
    //save() es un metodo de Spring Data JPA que guarda el objeto en la base de datos.
    //Si newPet no tiene ID, lo inserta como nuevo.
    //Si newPet ya tiene un ID existente , lo actualiza.
    public PetResponseDTO createPet(PetCreateDTO petCreateDTO) {
        Long userId = getCurrentUserID();
        User owner = userRepository.findById(userId)
                .orElseThrow();

        pet pet= new pet();
        pet.setName(petCreateDTO.getName()); //Extraemos el nombre
        pet.setDescription(petCreateDTO.getDescription());
        pet.setType(petCreateDTO.getType());
        pet.setImageUrl(petCreateDTO.getImageUrl());

        //Inicializar demas variables
        pet.setHunger(100);
        pet.setHappiness(100);
        pet.setHealth(100);
        pet.setEnergy(100);

        pet.setCreatedAt(LocalDateTime.now());
        pet.setLastInteraction(LocalDateTime.now());
        pet.setOwner(owner);

        pet savedPet = petRepository.save(pet);

        return convertToResponseDTO(savedPet) ;
    }

    private Long getCurrentUserID(){
        return 1L;
    }

    private PetResponseDTO convertToResponseDTO(pet pet) {
        PetResponseDTO dto = new PetResponseDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setDescription(pet.getDescription());
        dto.setType(pet.getType());
        dto.setImageUrl(pet.getImageUrl());
        dto.setHunger(pet.getHunger());
        dto.setHappiness(pet.getHappiness());
        dto.setHealth(pet.getHealth());
        dto.setEnergy(pet.getEnergy());
        dto.setLastInteraction(pet.getLastInteraction());
        dto.setCreatedAt(pet.getCreatedAt());

        UserSimpleDTO ownerDto = new UserSimpleDTO();
        ownerDto.setId(pet.getOwner().getId());
        ownerDto.setUsername(pet.getOwner().getUsername());
        dto.setOwner(ownerDto);

        return dto;
    }

    public List<pet> getAllPets() {
        return petRepository.findAll();
    }




}

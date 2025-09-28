package com.example.repasito1.application;

import com.example.repasito1.domain.pet;
import com.example.repasito1.infrastructure.petRepository;
import com.example.repasito1.service.petService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class petController {
    private final petService petService;
    private final petRepository petRepository;

    @Autowired
    public petController(petService petService, petRepository petRepository) { this.petService = petService;
        this.petRepository = petRepository;
    }

    //Leer data=GET
    //Insertar data=POST
    //Actualizar data=PUT
    //Eliminar data=DELETE

    //Crear animal
    @PostMapping() //Este metodo ya le da un prefijo implicito al endpoint
    //ResponseEntity es el tipado q utilizamos en java cuando vamos a crear endpoints
    //dentro del <> se devuelve lo q vamos a devolver en el body
    //De preferencia q el request no tenga el mismo nombre que en el service
    public ResponseEntity<pet> createPetController(@RequestBody pet newPet) {
        //Vamos a tener q mandar un body en la solicitud del postman, q sea de tipo pet.
        pet createPet = petService.createPet(newPet);
        return new ResponseEntity<>(createPet, HttpStatus.CREATED);
        //Esto es para que nos mande un mensaje http al crear el pet,
        // q nos diga que el pet ya ha sido creado
    }

    @GetMapping()
    public ResponseEntity<List<pet>> getAllPets() {
        List<pet> pets = petRepository.findAll();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }






}

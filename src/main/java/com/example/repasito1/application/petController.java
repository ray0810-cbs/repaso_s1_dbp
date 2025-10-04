package com.example.repasito1.application;

import com.example.repasito1.domain.pet;
import com.example.repasito1.dto.Pet.PetCreateDTO;
import com.example.repasito1.dto.Pet.PetResponseDTO;
import com.example.repasito1.dto.ResponseDTO;
import com.example.repasito1.infrastructure.petRepository;
import com.example.repasito1.service.petService;
import jakarta.validation.Valid;
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
    //Este metodo ya le da un prefijo implicito al endpoint
    //ResponseEntity es el tipado q utilizamos en java cuando vamos a crear endpoints
    //dentro del <> se devuelve lo q vamos a devolver en el body
    //De preferencia q el request no tenga el mismo nombre que en el service
    @PostMapping()
    //@Valid es para hacerle caso a las etiquetas @NotbNull y eso del dto
    //No lo ponemos para manejar nosotros mismos los errores que pueden ocurrir al ingresar valores
    public ResponseEntity<ResponseDTO<PetResponseDTO>> createPetController(@RequestBody PetCreateDTO newPet) {
        //Vamos a tener q mandar un body en la solicitud del postman, q sea de tipo pet.
        //PetResponseDTO createPet = petService.createPet(newPet);
        // return new ResponseEntity<>(createPet, HttpStatus.CREATED);
        //Esto es para que nos mande un mensaje http al crear el pet,
        // q nos diga que el pet ya ha sido creado
        try {
            //Crea a la mascota, 
            PetResponseDTO createdPet = petService.createPet(newPet);
            ResponseDTO<PetResponseDTO> response = new ResponseDTO<>();
            response.setData(createdPet);
            response.setMessage("Pet created successfully");
            response.setStatus(HttpStatus.CREATED.value());
            response.setError(null);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage()); // Print the error message to the log
            ResponseDTO<PetResponseDTO> response = new ResponseDTO<>();
            response.setData(null);
            response.setMessage("Invalid input data");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            log.error("Resource not found: {}", e.getMessage());
            ResponseDTO<PetResponseDTO> response = new ResponseDTO<>();
            response.setData(null);
            response.setMessage("Resource Not Found");
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            log.error("Error creating pet: {}", e.getMessage());
            ResponseDTO<PetResponseDTO> response = new ResponseDTO<>();
            response.setData(null);
            response.setMessage("Internal Server Error");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<pet>> getAllPets() {
        List<pet> pets = petRepository.findAll();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }






}

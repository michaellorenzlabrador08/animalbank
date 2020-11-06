package com.companyx.animalbank.controller;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.exception.InputIsEmpty;
import com.companyx.animalbank.repository.AnimalsRepository;
import com.companyx.animalbank.service.animal.AnimalService;
import com.companyx.animalbank.service.animal.AnimalServiceImpl;
import com.companyx.animalbank.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    @Autowired
    private AnimalServiceImpl animalService;



    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Animal> createAnimal(@RequestBody AnimalDto dto) {
        Response<Animal> resp = new Response<>();
        try {

            Animal animal = animalService.createAnimal(dto);
            if (animal != null) {
                resp.success(animalService.createAnimal(dto));
            } else {
                resp.failed("Create failed");
            }
        } catch (InputIsEmpty inputIsEmpty) {
            resp.failed(inputIsEmpty.getMessage());
        }
        return resp;
    }

    @GetMapping("/list")
    public Response<List<Animal>> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/hello")
    public String getHello() {
        return animalService.getHello();
    }


}

package com.companyx.animalbank.controller;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.repository.AnimalsRepository;
import com.companyx.animalbank.service.animal.AnimalService;
import com.companyx.animalbank.service.animal.AnimalServiceImpl;
import com.companyx.animalbank.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    @Autowired
    private AnimalServiceImpl animalService;

    @Autowired
    private AnimalsRepository animalsRepository;

    @PostMapping("/create")
    public Response<Animal> createAnimal(@RequestBody AnimalDto dto) {
        return animalService.createAnimal(dto);
    }

    @GetMapping("/list")
    public Response<List<Animal>> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/hello")
    public String getHello() {
        return "hello people!";
    }


}

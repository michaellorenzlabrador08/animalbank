package com.companyx.animalbank.controller;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.exception.InputIsEmpty;
import com.companyx.animalbank.service.animal.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    @Autowired
    private AnimalServiceImpl animalService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Animal create(@RequestBody AnimalDto dto) throws InputIsEmpty {
        Animal animal = animalService.create(dto);
        return animal;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Animal> list() {
        return animalService.list();
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Animal update(@RequestBody Animal newAnimal, @PathVariable Long id) {
        return animalService.update(newAnimal, id);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable Long id) {
        return animalService.delete(id);
    }


}

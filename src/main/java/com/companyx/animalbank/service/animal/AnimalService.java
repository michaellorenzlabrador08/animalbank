package com.companyx.animalbank.service.animal;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.util.Response;

import java.util.List;

public interface AnimalService {
    Response<Animal> createAnimal(AnimalDto dto);

    Response<List<Animal>> getAllAnimals();
}

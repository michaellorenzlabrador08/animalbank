package com.companyx.animalbank.service.animal;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.exception.InputIsEmpty;
import com.companyx.animalbank.util.Response;

import java.util.List;

public interface AnimalService {
    Animal createAnimal(AnimalDto dto) throws InputIsEmpty;

    Response<List<Animal>> getAllAnimals();

    String getHello();
}

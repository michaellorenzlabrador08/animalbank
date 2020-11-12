package com.companyx.animalbank.service.animal;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.exception.InputIsEmpty;

import java.util.List;

public interface AnimalService {
    Animal create(AnimalDto dto) throws InputIsEmpty;

    List<Animal> list();

    Animal update(Animal animal, Long id) throws InputIsEmpty;

    boolean delete(Long id);
}

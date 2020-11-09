package com.companyx.animalbank.service.animal;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.exception.InputIsEmpty;
import com.companyx.animalbank.repository.AnimalsRepository;
import com.companyx.animalbank.util.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalsRepository animalsRepository;


    @Override
    public Animal create(AnimalDto dto) throws InputIsEmpty {

        if (StringUtils.isBlank(dto.getName())) {
            throw new InputIsEmpty("Name cannot be null");
        }
        Animal newAnimal = new Animal(dto.getName(), dto.getColor(), dto.getAddress(), dto.getAge(), dto.getWeight());
        animalsRepository.save(newAnimal);
        return animalsRepository.findById(newAnimal.getId()).orElse(null);

    }


    @Override
    public List<Animal> list() {
        return (List<Animal>) animalsRepository.findAll();
    }


    @Override
    public Animal update(Animal newAnimal, Long id) {

        Animal animal = animalsRepository.findById(id).orElse(null);
        if (animal != null) {
            animal.setName(newAnimal.getName());
            animal.setAddress(newAnimal.getColor());
            animal.setAge(newAnimal.getAge());
            animal.setColor(newAnimal.getColor());
            animal.setWeight(newAnimal.getWeight());
            return animalsRepository.save(animal);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Animal toDelete = animalsRepository.findById(id).orElse(null);
        if (toDelete != null) {
            animalsRepository.delete(toDelete);
            return true;
        } else {
            return false;
        }
    }
}

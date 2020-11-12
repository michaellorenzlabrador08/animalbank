package com.companyx.animalbank.service.animal;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.exception.InputIsEmpty;
import com.companyx.animalbank.repository.AnimalsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalsRepository animalsRepository;

    @Override
    @Transactional
    public Animal create(AnimalDto dto) throws InputIsEmpty {
        if (StringUtils.isBlank(dto.getName())) {
            throw new InputIsEmpty("Name cannot be null");
        }
        Animal animal = new Animal(dto.getName(), dto.getColor(), dto.getAddress(), dto.getAge(), dto.getWeight());
        Animal newAnimal = animalsRepository.save(animal);
        return animalsRepository.findById(newAnimal.getId()).orElse(null);
    }


    @Override
    public List<Animal> list() {
        return (List<Animal>) animalsRepository.findAll();
    }


    @Override
    public Animal update(Animal newAnimal, Long id) throws InputIsEmpty {

        Animal animal = animalsRepository.findById(id).orElse(null);
        if (animal != null) {
            if (StringUtils.isBlank(newAnimal.getName())) {
                throw new InputIsEmpty("Animal name is null");
            }
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

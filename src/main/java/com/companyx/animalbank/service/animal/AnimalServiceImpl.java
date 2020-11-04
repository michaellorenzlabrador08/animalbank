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

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalsRepository animalsRepository;

    @Override
    public Response<Animal> createAnimal(AnimalDto dto) {
        Response<Animal> resp = new Response<>();
        try {
            if (StringUtils.isBlank(dto.getName())) {
                throw new InputIsEmpty("Name cannot be null");
            }
            Animal newAnimal = new Animal(dto.getName(), dto.getColor(), dto.getAddress(),0,10.5);
            animalsRepository.save(newAnimal);
            resp.success(animalsRepository.findByName(newAnimal.getName()));
        } catch (Exception ie) {
            resp.failed(ie.getMessage());
        }
        return resp;
    }

    @Override
    public Response<List<Animal>> getAllAnimals() {
        Response<List<Animal>> resp = new Response<>();
        try {
            List<Animal> all = (List<Animal>) animalsRepository.findAll();
            resp.success(all);
        } catch (Exception e) {
            resp.failed(e.getMessage());
        }
        return resp;
    }
}

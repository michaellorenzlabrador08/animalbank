package com.companyx.animalbank.service.animal;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.repository.AnimalsRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AnimalServiceTest {

    @InjectMocks
    private AnimalServiceImpl animalService;

    @Mock
    AnimalsRepository animalsRepository;

    @Test
    public void testCreate() throws Exception {
        AnimalDto dto = new AnimalDto("delf", "black", "marymount", 3, 2.0);
        Animal newAnimal = new Animal((long) 1, dto.getName(), dto.getColor(), dto.getAddress(), dto.getAge(), dto.getWeight());

        when(animalsRepository.save(newAnimal)).thenReturn(newAnimal);
        when(animalsRepository.findById(newAnimal.getId())).thenReturn(java.util.Optional.of(newAnimal));

        newAnimal = animalService.create(dto);
        assertNotNull(newAnimal);
    }

    @Test
    public void testList() throws Exception {
        List<Animal> animals = animalService.list();
        assertNotNull(animals);
    }
}
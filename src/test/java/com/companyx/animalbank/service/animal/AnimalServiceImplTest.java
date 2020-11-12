package com.companyx.animalbank.service.animal;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.exception.InputIsEmpty;
import com.companyx.animalbank.repository.AnimalsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceImplTest {

    @Mock
    private AnimalsRepository animalsRepository;

    @InjectMocks
    private AnimalServiceImpl animalService;

    @Test
    public void test_create() throws Exception {
        AnimalDto dto = new AnimalDto("delf", "black", "marymount", 3, 2.0);
        Animal newAnimal = new Animal((long) 1, dto.getName(), dto.getColor(), dto.getAddress(), dto.getAge(), dto.getWeight());

        when(animalsRepository.save(newAnimal)).thenReturn(newAnimal);
        when(animalsRepository.findById(newAnimal.getId())).thenReturn(java.util.Optional.of(newAnimal));

        newAnimal = animalService.create(dto);
        verify(animalsRepository).save(newAnimal);
        assertNotNull(newAnimal);
    }

    @Test
    public void test_list() {
        //when
        List<Animal> list = new ArrayList<>();
        list.add(new Animal((long) 1, "michael", "yellow", "simei", 2, 5.9));
        list.add(new Animal((long) 2, "michael", "yellow", "simei", 2, 5.9));
        list.add(new Animal((long) 3, "michael", "yellow", "simei", 2, 5.9));

        when(animalsRepository.findAll()).thenReturn(list);
        //then
        List<Animal> myList = animalService.list();

        //verify
        assertEquals(list, myList);
        verify(animalsRepository).findAll();
    }

    @Test(expected = InputIsEmpty.class)
    public void test_update_ifNameIsEmpty() throws InputIsEmpty {

        //when
        Animal newAnimal = new Animal((long) 1, "", "yellow", "simei", 2, 5.9);
        when(animalsRepository.findById(newAnimal.getId())).thenReturn(java.util.Optional.ofNullable(newAnimal));
        //when(animalsRepository.save(newAnimal)).thenReturn(newAnimal);

        //then
        Animal updatedAnimal = animalService.update(newAnimal, newAnimal.getId());

        verify(animalsRepository).findById(newAnimal.getId());
        verify(animalsRepository).save(newAnimal);
    }

    @Test
    public void test_update() throws InputIsEmpty {

        //when
        Animal newAnimal = new Animal((long) 1, "newName", "yellow", "simei", 2, 5.9);
        when(animalsRepository.findById(newAnimal.getId())).thenReturn(java.util.Optional.ofNullable(newAnimal));
        when(animalsRepository.save(newAnimal)).thenReturn(newAnimal);

        //then
        Animal updatedAnimal = animalService.update(newAnimal, newAnimal.getId());

        //verify
        assertNotNull(updatedAnimal);
        verify(animalsRepository).findById(newAnimal.getId());
        verify(animalsRepository).save(newAnimal);
    }


    @Test
    public void test_delete() {
        //when
        Animal newAnimal = new Animal((long) 1, "newName", "yellow", "simei", 2, 5.9);
        when(animalsRepository.findById(newAnimal.getId())).thenReturn(java.util.Optional.ofNullable(newAnimal));
        //then
        boolean result = animalService.delete((long) 1);
        //verify
        assertTrue(result);
        verify(animalsRepository).delete(newAnimal);

    }

    @Test
    public void test_delete_nullAnimal() {
        //when
        Animal newAnimal = new Animal((long) 1, "newName", "yellow", "simei", 2, 5.9);
        when(animalsRepository.findById(newAnimal.getId())).thenReturn(java.util.Optional.ofNullable(null));
        //then
        boolean result = animalService.delete((long) 1);
        //verify
        assertFalse(result);
    }
}
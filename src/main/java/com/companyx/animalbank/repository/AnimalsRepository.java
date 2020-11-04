package com.companyx.animalbank.repository;

import com.companyx.animalbank.entity.Animal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsRepository extends CrudRepository<Animal, Long> {

    @Query("SELECT a from Animal a WHERE a.name LIKE %:animalName%")
    Animal findByName(String animalName);

}

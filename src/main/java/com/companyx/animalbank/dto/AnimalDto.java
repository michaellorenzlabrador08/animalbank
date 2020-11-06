package com.companyx.animalbank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AnimalDto implements Serializable {


    private Long id;

    @NotNull
    private String name;

    private String color;
    private String address;
    private Integer age;
    private Double weight;


    public AnimalDto(String name, String color, String address, Integer age, Double weight) {
        this.name = name;
        this.color = color;
        this.address = address;
        this.age = age;
        this.weight = weight;
    }


}

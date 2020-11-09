package com.companyx.animalbank.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "animals")
@Data
public class Animal extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "color")
    private String color;
    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Double weight;

    public Animal() {
        super();
    }

    public Animal(String name, String color, String address, Integer age, Double weight) {
        super();
        this.name = name;
        this.color = color;
        this.address = address;
        this.age = age;
        this.weight = weight;
    }

    public Animal(Long id, String name, String color, String address, Integer age, Double weight) {
        super.setId(id);
        this.name = name;
        this.color = color;
        this.address = address;
        this.age = age;
        this.weight = weight;
    }
}

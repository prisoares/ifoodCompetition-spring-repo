package com.thoughtworks.ifoodcompetition.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String address;

    public Restaurant(){}

    public Restaurant(String name, String description, String address, Long id){
        this.name = name;
        this.description = description;
        this.address = address;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void update(Restaurant updatedRestaurant) {
        this.name = updatedRestaurant.name;
        this.description = updatedRestaurant.description;
        this.address = updatedRestaurant.address;
    }
}

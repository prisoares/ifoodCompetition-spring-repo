package com.thoughtworks.ifoodcompetition.model;

import com.thoughtworks.ifoodcompetition.infraestructure.CnpjValidator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String address;
    private String cnpj;

    public Restaurant(){}

    public Restaurant(String cnpj, String name, String description, String address, Long id){
        this.cnpj = cnpj;
        this.name = name;
        this.description = description;
        this.address = address;
        this.id = id;
    }

    public String getCnpj() { return cnpj; }

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


    public void update(Restaurant updatedRestaurant) {
        this.cnpj = updatedRestaurant.cnpj;
        this.name = updatedRestaurant.name;
        this.description = updatedRestaurant.description;
        this.address = updatedRestaurant.address;
    }


}

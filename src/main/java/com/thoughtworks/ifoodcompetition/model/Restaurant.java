package com.thoughtworks.ifoodcompetition.model;

public class Restaurant {

    private String name;
    private String description;
    private String address;
    private Long id;

    public Restaurant(){

    }

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

    public void setName(String name) {
        this.name = name;
    }
}

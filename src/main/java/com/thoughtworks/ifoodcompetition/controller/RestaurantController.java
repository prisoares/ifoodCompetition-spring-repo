package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.infraestructure.RestaurantRepository;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantController {

    private final RestaurantRepository repository;

    public RestaurantController(RestaurantRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.PUT)
    public Restaurant createRestaurant(@RequestBody Restaurant newRestaurant) {
        return repository.save(newRestaurant);
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
    public Restaurant getRestaurantByID(@PathVariable("id") Long id) {
        Restaurant restaurante = findRestaurantById(id);
        return restaurante;
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.POST)
    public Restaurant updateRestaurant(@PathVariable("id") Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurante = findRestaurantById(id);
        return restaurante;
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.DELETE)
    public String deleteRestaurant(@PathVariable("id") Long id) {
        return  deleteRestaurantById(id);
    }

    private Restaurant findRestaurantById(Long id){
        return repository.findById(id);
        //return new Restaurant("Segunda Mesa", "comida boa", "rua dois", id);
    }

    private String deleteRestaurantById(Long id) {
        return "Restaurante " + id + " foi removido com sucesso.";
    }

}

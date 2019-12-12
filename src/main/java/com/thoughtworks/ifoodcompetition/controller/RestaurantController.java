package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.infraestructure.RestaurantRepository;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return repository.findById(id);
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.POST)
    public Restaurant updateRestaurantById(@PathVariable("id") Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant currentRestaurant = repository.findById(id);
        currentRestaurant.update(updatedRestaurant);
        Restaurant savedRestaurant = repository.save(currentRestaurant);
        return savedRestaurant;
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    public Restaurant updateRestaurant(@RequestBody Restaurant updatedRestaurant) {
        return repository.save(updatedRestaurant);
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.DELETE)
    public String deleteRestaurant(@PathVariable("id") Long id) {
        return  deleteRestaurantById(id);
    }

    private String deleteRestaurantById(Long id) {
        try {
            repository.deleteById(id);
            return "Restaurante " + id + " foi removido com sucesso.";
        } catch (EmptyResultDataAccessException e) {
            return "Houve um problema ao deletar o restaurante.";
        }
    }

}

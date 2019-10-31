package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantController {

    @RequestMapping(value = "/restaurant", method = RequestMethod.PUT)
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return insertRestaurant(restaurant);
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

    private Restaurant findRestaurantById(Long id){
        return new Restaurant("Segunda Mesa", "comida boa", "rua dois", id);
    }

    private Restaurant insertRestaurant(Restaurant newRestaurant){
        Long id = 9L;
        return new Restaurant(newRestaurant.getName(), newRestaurant.getDescription(), newRestaurant.getAddress(), id);
    }

}

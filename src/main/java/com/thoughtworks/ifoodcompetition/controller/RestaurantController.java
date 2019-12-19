package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.infraestructure.CardapioRepository;
import com.thoughtworks.ifoodcompetition.infraestructure.RestaurantRepository;
import com.thoughtworks.ifoodcompetition.model.Cardapio;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RestaurantController {

    private static final String RESTAURANT_SUCCESSFULLY_DELETED_MESSAGE = "{\"message\": \"Restaurante foi removido com sucesso.\"}";
    private static final String RESTAURANT_NOT_FOUND_MESSAGE = "Houve um problema ao deletar o restaurante.";
    private final RestaurantRepository restaurantRepository;
    private final CardapioRepository cardapioRepository;


    public RestaurantController(RestaurantRepository restaurantRepository, CardapioRepository cardapioRepository) {
        this.restaurantRepository = restaurantRepository;
        this.cardapioRepository = cardapioRepository;
    }

    @RequestMapping(value = "/restaurant/{id}/cardapio", method = RequestMethod.PUT)
    public Cardapio createCardapio(@PathVariable("id") Long id, @RequestBody Cardapio newCardapio) {
        newCardapio.setRestaurantId(id);
        return cardapioRepository.save(newCardapio);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.PUT)
    public Restaurant createRestaurant(@RequestBody Restaurant newRestaurant) {
        return restaurantRepository.save(newRestaurant);
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
    public Restaurant getRestaurantByID(@PathVariable("id") Long id) {
        return restaurantRepository.findById(id);
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.POST)
    public Restaurant updateRestaurantById(@PathVariable("id") Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant currentRestaurant = restaurantRepository.findById(id);
        currentRestaurant.update(updatedRestaurant);
        Restaurant savedRestaurant = restaurantRepository.save(currentRestaurant);
        return savedRestaurant;
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    public Restaurant updateRestaurant(@RequestBody Restaurant updatedRestaurant) {
        return restaurantRepository.save(updatedRestaurant);
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.DELETE)
    public String deleteRestaurant(@PathVariable("id") Long id) throws Exception {
        try {
            restaurantRepository.deleteById(id);
            return RESTAURANT_SUCCESSFULLY_DELETED_MESSAGE;
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, RESTAURANT_NOT_FOUND_MESSAGE);
        }
    }
}

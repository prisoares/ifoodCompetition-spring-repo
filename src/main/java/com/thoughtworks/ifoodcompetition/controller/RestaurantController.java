package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.infraestructure.CnpjValidator;
import com.thoughtworks.ifoodcompetition.infraestructure.PratoRepository;
import com.thoughtworks.ifoodcompetition.infraestructure.RestaurantRepository;
import com.thoughtworks.ifoodcompetition.model.Prato;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class RestaurantController {

    private static final String RESTAURANT_SUCCESSFULLY_DELETED_MESSAGE = "{\"message\": \"Restaurante foi removido com sucesso.\"}";
    private static final String PRATO_SUCCESSFULLY_DELETED_MESSAGE = "{\"message\": \"Prato foi removido com sucesso.\"}";
    private static final String RESTAURANT_NOT_FOUND_MESSAGE = "Houve um problema ao deletar o restaurante.";
    private static final String PRATO_NOT_FOUND_MESSAGE = "Houve um problema ao deletar o prato.";
    private final RestaurantRepository restaurantRepository;
    private final PratoRepository pratoRepository;
    private final CnpjValidator cnpjValidator;

    public RestaurantController(CnpjValidator cnpj, RestaurantRepository restaurantRepository, PratoRepository pratoRepository) {
        this.cnpjValidator = cnpj;
        this.restaurantRepository = restaurantRepository;
        this.pratoRepository = pratoRepository;
    }

    @RequestMapping(value = "/restaurant/{id}/prato", method = RequestMethod.PUT)
    public Prato createPrato(@PathVariable("id") Long id, @RequestBody Prato newPrato) throws Exception {
        validateRestaurant(id);
        newPrato.setRestaurantId(id);
        return pratoRepository.save(newPrato);
    }

    @RequestMapping(value = "/restaurant/{idRestaurant}/prato/{idPrato}", method = RequestMethod.GET)
    public Prato getPrato(@PathVariable Long idRestaurant, @PathVariable Long idPrato) throws Exception {
        return pratoRepository.findByRestaurantIdAndId(idRestaurant, idPrato);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.PUT)
    public Restaurant createRestaurant(@RequestBody Restaurant newRestaurant) {
        return restaurantRepository.save(newRestaurant);
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.GET)
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @RequestMapping(value = "/restaurant/{restaurantId}/cardapio", method = RequestMethod.GET)
    public List<Prato> getCardapio(@PathVariable("restaurantId") Long restaurantId) {
        return pratoRepository.findByRestaurantIdEquals(restaurantId);
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
    public Restaurant getRestaurantByID(@PathVariable("id") Long id) throws Exception {
        return validateRestaurant(id);
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.POST)
    public Restaurant updateRestaurantById(@PathVariable("id") Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant currentRestaurant = restaurantRepository.findById(id);
        currentRestaurant.update(updatedRestaurant);
        Restaurant actualRestaurant = restaurantRepository.save(currentRestaurant);
        return actualRestaurant;
    }

    @RequestMapping(value = "/restaurant/{restaurantId}/prato/{pratoId}", method = RequestMethod.POST)
    public Prato updatePratoById(@PathVariable("restaurantId") Long restaurantId, @PathVariable("pratoId") Long pratoId, @RequestBody Prato updatedPrato) {
        Prato currentPrato = pratoRepository.findByRestaurantIdAndId(restaurantId,pratoId);
        currentPrato.update(updatedPrato);
        Prato actualPrato = pratoRepository.save(currentPrato);
        return actualPrato;
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.DELETE)
    public String deleteRestaurant(@PathVariable("id") Long id) {
        try {
            restaurantRepository.deleteById(id);
            return RESTAURANT_SUCCESSFULLY_DELETED_MESSAGE;
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, RESTAURANT_NOT_FOUND_MESSAGE);
        }
    }

    @Transactional
    @RequestMapping(value = "/restaurant/{restaurantId}/prato/{pratoId}", method = RequestMethod.DELETE)
    public String deletePrato(@PathVariable("restaurantId") Long restaurantId, @PathVariable("pratoId") Long pratoId) {

            if(pratoRepository.deleteByRestaurantIdAndId(restaurantId, pratoId) > 0) {
                return PRATO_SUCCESSFULLY_DELETED_MESSAGE;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, PRATO_NOT_FOUND_MESSAGE);
            }
    }

    private Restaurant validateRestaurant(@PathVariable("id") Long id) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(id);
        if(restaurant == null) {
            throw new Exception("Restaurante não existe.");
        } else {
            return restaurant;
        }
    }


}

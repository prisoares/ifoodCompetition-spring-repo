package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.infraestructure.RestaurantRepository;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.aspectj.apache.bcel.util.Repository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestaurantControllerTest {

    private RestaurantRepository repository;
    private RestaurantController controller;

    @Before
    public void setup() {
        repository = mock(RestaurantRepository.class);
        controller = new RestaurantController(repository);
    }

    @Test
    public void shouldInsertRestaurant() {
        //Given
        Restaurant newRestaurant = mock(Restaurant.class);
        Restaurant expectedRestaurant = mock(Restaurant.class);
        when(repository.save(newRestaurant)).thenReturn(expectedRestaurant);

        //When
        Restaurant restaurant = controller.createRestaurant(newRestaurant);

        //Then
        assertThat(restaurant, is(expectedRestaurant));
    }

    @Test
    public void shouldGetRestaurantById(){
        /* //Given
        RestaurantController controller = new RestaurantController(repository);
        Long id = 10L;
        String name = "Segunda Mesa";
        String description = "comida boa";
        String address = "rua dois";

        //When
        Restaurant restaurant = controller.getRestaurantByID(id);

        //Then
        assertThat(restaurant.getId(), is(id));
        assertThat(restaurant.getName(), is(name));
        assertThat(restaurant.getDescription(), is(description));
        assertThat(restaurant.getAddress(), is(address));*/
    }

    @Test
    public void shouldUpdateRestaurant(){
        /*Given
        RestaurantController controller = new RestaurantController(repository);
        Long id = 10L;
        String name = "Segunda Mesa";
        String description = "comida boa";
        String address = "rua dois";

        Restaurant updatedRestaurante = new Restaurant(name, description, address, id);

        //When
        Restaurant restaurant = controller.updateRestaurant(id, updatedRestaurante);

        //Then
        assertThat(restaurant.getId(), is(id));
        assertThat(restaurant.getName(), is(name));
        assertThat(restaurant.getDescription(), is(description));
        assertThat(restaurant.getAddress(), is(address));*/
    }

    @Test
    public void shouldDeleteRestaurant(){
        //Given
        RestaurantController controller = new RestaurantController(repository);
        Long id = 10L;

        //When
        String deletedRestaurant = controller.deleteRestaurant(id);

        //Then
        assertThat(deletedRestaurant.toString(), is("Restaurante " + id + " foi removido com sucesso."));
    }

}
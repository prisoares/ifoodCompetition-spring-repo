package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantControllerTest {

    @Test
    public void shouldGetRestaurantById(){
        //Given
        RestaurantController controller = new RestaurantController();
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
        assertThat(restaurant.getAddress(), is(address));
    }

    @Test
    public void shouldUpdateRestaurant(){
        //Given
        RestaurantController controller = new RestaurantController();
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
        assertThat(restaurant.getAddress(), is(address));
    }


}
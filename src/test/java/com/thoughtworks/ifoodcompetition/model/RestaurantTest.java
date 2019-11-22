package com.thoughtworks.ifoodcompetition.model;

import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTest {

    @Test
    public void shouldCreateRestaurantWithSuccess(){
        Restaurant restaurant = new Restaurant("Primeira Mesa",
                "Free buffet", "Calle 2", 1L);
        assertThat(restaurant.getName(), is("Primeira Mesa"));
        assertThat(restaurant.getDescription(), is("Free buffet"));
        assertThat(restaurant.getAddress(), is("Calle 2"));
        assertThat(restaurant.getId(), is(1L));
    }

    @Test
    public void shouldSetId(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        assertThat(restaurant.getId(), is(1L));
    }

    @Test
    public void updateFromRestaurant(){
        Restaurant currentRestaurant = new Restaurant("Segunda Mesa",
                "Free Beer", "Calle 3", 2L);;
        Restaurant updatedRestaurant = new Restaurant("Primeira Mesa",
                "Free buffet", "Calle 2", null);

        currentRestaurant.update(updatedRestaurant);

        assertThat(currentRestaurant.getName(), is(updatedRestaurant.getName()));
        assertThat(currentRestaurant.getDescription(), is(updatedRestaurant.getDescription()));
        assertThat(currentRestaurant.getAddress(), is(updatedRestaurant.getAddress()));
        assertThat(currentRestaurant.getId(), is(2L));
    }
}
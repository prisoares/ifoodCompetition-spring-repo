package com.thoughtworks.ifoodcompetition.model;

import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTest {

    @Test
    public void shouldCreateRestaurantWithDefaultConstructor(){
        Restaurant restaurant = new Restaurant();
        assertNotNull(restaurant);
        assertNull(restaurant.getCnpj());
        assertNull(restaurant.getName());
        assertNull(restaurant.getDescription());
        assertNull(restaurant.getAddress());
        assertNull(restaurant.getId());
    }

    @Test
    public void shouldCreateRestaurantWithSuccess(){
        Restaurant restaurant = new Restaurant("123456", "Primeira Mesa",
                "Free buffet", "Calle 2", 1L);
        assertThat(restaurant.getCnpj(), is("123456"));
        assertThat(restaurant.getName(), is("Primeira Mesa"));
        assertThat(restaurant.getDescription(), is("Free buffet"));
        assertThat(restaurant.getAddress(), is("Calle 2"));
        assertThat(restaurant.getId(), is(1L));
    }

    @Test
    public void updateFromRestaurant(){
        Restaurant currentRestaurant = new Restaurant("123456","Segunda Mesa",
                "Free Beer", "Calle 3", 2L);;
        Restaurant updatedRestaurant = new Restaurant("200000","Primeira Mesa",
                "Free buffet", "Calle 2", null);

        currentRestaurant.update(updatedRestaurant);

        assertThat(currentRestaurant.getCnpj(), is(updatedRestaurant.getCnpj()));
        assertThat(currentRestaurant.getName(), is(updatedRestaurant.getName()));
        assertThat(currentRestaurant.getDescription(), is(updatedRestaurant.getDescription()));
        assertThat(currentRestaurant.getAddress(), is(updatedRestaurant.getAddress()));
        assertThat(currentRestaurant.getId(), is(2L));
    }
}
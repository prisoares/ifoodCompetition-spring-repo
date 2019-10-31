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
}
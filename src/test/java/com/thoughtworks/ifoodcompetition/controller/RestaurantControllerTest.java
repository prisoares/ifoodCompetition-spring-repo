package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.infraestructure.CardapioRepository;
import com.thoughtworks.ifoodcompetition.infraestructure.RestaurantRepository;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;

import org.springframework.dao.EmptyResultDataAccessException;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {

    private RestaurantRepository restaurantRepository;
    private CardapioRepository cardapioRepository;
    private RestaurantController controller;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        restaurantRepository = mock(RestaurantRepository.class);
        cardapioRepository = mock(CardapioRepository.class);
        controller = new RestaurantController(restaurantRepository, cardapioRepository);
    }

    @Test
    public void shouldInsertRestaurant() {
        //Given
        Restaurant newRestaurant = mock(Restaurant.class);
        Restaurant expectedRestaurant = mock(Restaurant.class);
        when(restaurantRepository.save(newRestaurant)).thenReturn(expectedRestaurant);

        //When
        Restaurant restaurant = controller.createRestaurant(newRestaurant);

        //Then
        assertThat(restaurant, is(expectedRestaurant));
    }

    @Test
    public void shouldGetRestaurantById() {
        //Given
        Long id = 1L;
        Restaurant expectedRestaurant = mock(Restaurant.class);
        when(restaurantRepository.findById(id)).thenReturn(expectedRestaurant);

        //When
        Restaurant restaurant = controller.getRestaurantByID(id);

        //Then
        assertThat(restaurant, is(expectedRestaurant));
    }

    @Test
    public void shouldUpdateRestaurant(){
        //Given
        Restaurant updatedRestaurant = mock(Restaurant.class);
        Restaurant oldRestaurant = mock(Restaurant.class);
        when(restaurantRepository.save(oldRestaurant)).thenReturn(updatedRestaurant);
        //When
        Restaurant restaurant = controller.updateRestaurant(oldRestaurant);

        //Then
        assertThat(restaurant, is(updatedRestaurant));
    }

    @Test
    public void shouldUpdateRestaurantById(){
        //Given
        Long id = 1L;
        Restaurant updatedRestaurant = mock(Restaurant.class);
        Restaurant currentRestaurant = mock(Restaurant.class);
        Restaurant savedRestaurant = mock(Restaurant.class);
        when(restaurantRepository.findById(id)).thenReturn(currentRestaurant);
        when(restaurantRepository.save(currentRestaurant)).thenReturn(savedRestaurant);

        //When
        Restaurant restaurant = controller.updateRestaurantById(id, updatedRestaurant);

        //Then
        assertThat(restaurant, is(savedRestaurant));
        verify(currentRestaurant).update(updatedRestaurant);

        InOrder order = inOrder(restaurantRepository, currentRestaurant, restaurantRepository);
        order.verify(restaurantRepository).findById(id);
        order.verify(currentRestaurant).update(updatedRestaurant);
        order.verify(restaurantRepository).save(currentRestaurant);

    }

    @Test
    public void shouldDeleteRestaurant() throws Exception {
        //Given
        Long id = 3L;
        doNothing().when(restaurantRepository).deleteById(id);

        //When
        String deletedRestaurant = controller.deleteRestaurant(id);

        //Then
        verify(restaurantRepository).deleteById(id);
        assertThat(deletedRestaurant, is("{\"message\": \"Restaurante foi removido com sucesso.\"}"));
    }

    @Test
    public void shouldNotDeleteRestaurant() throws Exception {
        //Given
        Long id = 3L;
        doThrow(EmptyResultDataAccessException.class).when(restaurantRepository).deleteById(id);
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Houve um problema ao deletar o restaurante.");

        //When
        controller.deleteRestaurant(id);
    }
}
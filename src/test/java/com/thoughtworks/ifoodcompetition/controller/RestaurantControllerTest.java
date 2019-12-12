package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.infraestructure.RestaurantRepository;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.aspectj.apache.bcel.util.Repository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import org.springframework.dao.EmptyResultDataAccessException;

import javax.naming.ldap.Rdn;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    public void shouldGetRestaurantById() {
        //Given
        Long id = 1L;
        Restaurant expectedRestaurant = mock(Restaurant.class);
        when(repository.findById(id)).thenReturn(expectedRestaurant);

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
        when(repository.save(oldRestaurant)).thenReturn(updatedRestaurant);
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
        when(repository.findById(id)).thenReturn(currentRestaurant);
        when(repository.save(currentRestaurant)).thenReturn(savedRestaurant);

        //When
        Restaurant restaurant = controller.updateRestaurantById(id, updatedRestaurant);

        //Then
        assertThat(restaurant, is(savedRestaurant));
        verify(currentRestaurant).update(updatedRestaurant);

        InOrder order = inOrder(repository, currentRestaurant, repository);
        order.verify(repository).findById(id);
        order.verify(currentRestaurant).update(updatedRestaurant);
        order.verify(repository).save(currentRestaurant);

    }

    @Test
    public void shouldDeleteRestaurant(){
        //Given
        Long id = 3L;
        when(repository.deleteById(id)).thenReturn(true);

        //When
        String deletedRestaurant = controller.deleteRestaurant(id);

        //Then
        assertThat(deletedRestaurant, is("Restaurante " + id + " foi removido com sucesso."));
    }

    @Test
    public void shouldNotDeleteRestaurant(){
        //Given
        Long id = 3L;
        when(repository.deleteById(id)).thenThrow(EmptyResultDataAccessException.class);

        //When
        String deletedRestaurant = controller.deleteRestaurant(id);

        //Then
        assertThat(deletedRestaurant, is("Houve um problema ao deletar o restaurante."));
    }


}
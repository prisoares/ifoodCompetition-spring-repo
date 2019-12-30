package com.thoughtworks.ifoodcompetition.controller;

import com.thoughtworks.ifoodcompetition.infraestructure.PratoRepository;
import com.thoughtworks.ifoodcompetition.infraestructure.RestaurantRepository;
import com.thoughtworks.ifoodcompetition.model.Prato;
import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {

    private RestaurantRepository restaurantRepository;
    private PratoRepository pratoRepository;
    private RestaurantController controller;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        restaurantRepository = mock(RestaurantRepository.class);
        pratoRepository = mock(PratoRepository.class);
        controller = new RestaurantController(restaurantRepository, pratoRepository);
    }

    @Test
    public void shouldCreateRestaurant() {
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
    public void shouldCreatePrato() throws Exception{
        //Given
        Long idRestaurante = 1L;
        Restaurant restaurant = mock(Restaurant.class);
        Prato newPrato = mock(Prato.class);
        Prato expectedPrato = mock(Prato.class);
        when(restaurantRepository.findById(idRestaurante)).thenReturn(restaurant);
        when(pratoRepository.save(newPrato)).thenReturn(expectedPrato);

        //When
        Prato prato = controller.createPrato(idRestaurante, newPrato);

        //Then
        assertThat(prato, is(expectedPrato));
        verify(newPrato).setRestaurantId(idRestaurante);

        InOrder order = inOrder(restaurantRepository, pratoRepository);
        order.verify(restaurantRepository).findById(idRestaurante);
        order.verify(pratoRepository).save(newPrato);
    }

    @Test
    public void shouldNotCreatePratoWithInvalidRestaurant() throws Exception {
        //Given
        Long idRestaurante = 1L;
        Prato newPrato = mock(Prato.class);
        when(restaurantRepository.findById(idRestaurante)).thenReturn(null);
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Restaurante não existe.");
        //When
        controller.createPrato(idRestaurante, newPrato);
    }

    @Test
    public void shouldGetPrato() throws Exception {
        //Given
        Long idRestaurant = 1L;
        Long idPrato = 2L;
        Prato expectedPrato = mock(Prato.class);
        when(pratoRepository.findByRestaurantIdAndId(idRestaurant, idPrato)).thenReturn(expectedPrato);

        //When
        Prato prato = controller.getPrato(idRestaurant, idPrato);

        //Then
        assertThat(prato, is(expectedPrato));
    }

    @Test
    public void shouldGetRestaurantById() throws Exception {
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
    public void shouldThrowNotFoundExceptionWhenGetInvalidRestaurant() throws Exception {
        //Given
        Long id = 1L;
        when(restaurantRepository.findById(id)).thenReturn(null);
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Restaurante não existe.");

        //When
        controller.getRestaurantByID(id);
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
    public void shouldUpdatePrato() {
        //Given
        Long restaurantId = 1L;
        Long pratoId = 1L;
        Prato currentPrato = mock(Prato.class);
        Prato updatedPrato = mock(Prato.class);
        when(pratoRepository.findByRestaurantIdAndId(restaurantId, pratoId)).thenReturn(currentPrato);
        when(pratoRepository.save(currentPrato)).thenReturn(updatedPrato);

        //When
        Prato prato = controller.updatePratoById(restaurantId, pratoId, updatedPrato);

        //Then
        assertThat(prato, is(updatedPrato));
        verify(currentPrato).update(updatedPrato);

        InOrder order = inOrder(pratoRepository, currentPrato, pratoRepository);
        order.verify(pratoRepository).findByRestaurantIdAndId(restaurantId,pratoId);
        order.verify(currentPrato).update(updatedPrato);
        order.verify(pratoRepository).save(currentPrato);

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
    public void shouldDeletePrato() {
        //Given
        Long restaurantId = 1L;
        Long pratoId = 1L;
        doReturn(1L).when(pratoRepository).deleteByRestaurantIdAndId(restaurantId, pratoId);

        //When
        String deletedPrato = controller.deletePrato(restaurantId, pratoId);

        //Then
        verify(pratoRepository).deleteByRestaurantIdAndId(restaurantId, pratoId);
        assertThat(deletedPrato, is("{\"message\": \"Prato foi removido com sucesso.\"}"));

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

    @Test
    public void shouldNotDeletePrato() throws Exception {
        //Given
        Long restaurantId = 1L;
        Long pratoId = 1L;
        doReturn(0L).when(pratoRepository).deleteByRestaurantIdAndId(restaurantId, pratoId);
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Houve um problema ao deletar o prato.");

        //When
        controller.deletePrato(restaurantId, pratoId);
    }

    @Test
    public void shouldListAllRestaurant() {
        //Given
        List<Restaurant> expectedRestaurants = Arrays.asList(mock(Restaurant.class), mock(Restaurant.class));
        when(restaurantRepository.findAll()).thenReturn(expectedRestaurants);

        //When
        List<Restaurant> restaurants = controller.getRestaurants();
        //Then
        assertThat(restaurants.isEmpty(), is(false));
        assertThat(restaurants.size(), is(2));
    }

    @Test
    public void shouldListNoneRestaurant() {
        //Given
        List<Restaurant> expectedRestaurants = Collections.emptyList();
        when(restaurantRepository.findAll()).thenReturn(expectedRestaurants);

        //When
        List<Restaurant> restaurants = controller.getRestaurants();
        //Then
        assertThat(restaurants.isEmpty(), is(true));
        assertThat(restaurants.size(), is(0));
    }

    @Test
    public void shouldListAllPratos() {
        //Given
        Long restaurantId = 1L;
        List<Prato> expectedCardapio = Arrays.asList(mock(Prato.class), mock(Prato.class));
        when(pratoRepository.findByRestaurantIdEquals(restaurantId)).thenReturn(expectedCardapio);

        //When
        List<Prato> cardapio = controller.getCardapio(restaurantId);

        //Then
        assertThat(cardapio.isEmpty(), is(false));
        assertThat(cardapio.size(), is(2));
    }

    @Test
    public void shouldListNonePratos() {
        //Given
        Long restaurantId = 1L;
        List<Prato> expectedCardapio = Collections.emptyList();
        when(pratoRepository.findByRestaurantIdEquals(restaurantId)).thenReturn(expectedCardapio);

        //When
        List<Prato> cardapio = controller.getCardapio(restaurantId);

        //Then
        assertThat(cardapio.isEmpty(), is(true));
        assertThat(cardapio.size(), is(0));
    }

}
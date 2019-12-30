package com.thoughtworks.ifoodcompetition.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PratoTest {

    @Test
    public void shouldCreatePratoWithDefaultConstructor(){
        Prato prato = new Prato();
        assertNotNull(prato);
        assertNull(prato.getId());
        assertNull(prato.getPrato());
        assertNull(prato.getTipo());
        assertNull(prato.getDescricao());
        assertNull(prato.getValor());
        assertNull(prato.getRestaurantId());
    }

    @Test
    public void shouldCreatePratoWithSuccess(){
        Prato prato = new Prato(1L, "Ala minuta", "Pratos Quentes", "Arroz, feijao, batata frita, carne, salda de alface e tomate", "R$20", 1L);
        assertThat(prato.getId(), is(1L));
        assertThat(prato.getPrato(), is("Ala minuta"));
        assertThat(prato.getTipo(), is("Pratos Quentes"));
        assertThat(prato.getDescricao(), is("Arroz, feijao, batata frita, carne, salda de alface e tomate"));
        assertThat(prato.getValor(), is("R$20"));
        assertThat(prato.getRestaurantId(), is(1L));
    }

    @Test
    public void shouldSetRestaurantId(){
        //Given
        Long newRestaurantId = 1L;
        Prato prato = new Prato();
        //When
        prato.setRestaurantId(newRestaurantId);
        //Then
        assertThat(prato.getRestaurantId(), is(newRestaurantId));
    }

    @Test
    public void updateFromPrato(){
        Prato currentPrato = new Prato(1L, "Ala minuta", "Comida Quente", "Arroz, feijao", "R$20", 1L);
        Prato updatedPrato = new Prato(null, "Ala hora", "Comida FRIA", "Arroz, feijao", "R$20", null);

        currentPrato.update(updatedPrato);

        assertThat(currentPrato.getId(), is(1L));
        assertThat(currentPrato.getPrato(), is(updatedPrato.getPrato()));
        assertThat(currentPrato.getTipo(), is(updatedPrato.getTipo()));
        assertThat(currentPrato.getDescricao(), is(updatedPrato.getDescricao()));
        assertThat(currentPrato.getValor(), is(updatedPrato.getValor()));
        assertThat(currentPrato.getRestaurantId(), is(1L));
    }
}

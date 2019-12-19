package com.thoughtworks.ifoodcompetition.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CardapioTest {

    @Test
    public void shouldCreateCardapioWithSuccess(){
        Cardapio cardapio = new Cardapio(1L, "Ala minuta", "Pratos Quentes", "Arroz, feijao, batata frida, carne, salda de alface e tomate", "R$20", 1L);
        assertThat(cardapio.getId(), is(1L));
        assertThat(cardapio.getPrato(), is("Ala minuta"));
        assertThat(cardapio.getTipo(), is("Pratos Quentes"));
        assertThat(cardapio.getDescricao(), is("Arroz, feijao, batata frida, carne, salda de alface e tomate"));
        assertThat(cardapio.getValor(), is("R$20"));
        assertThat(cardapio.getRestaurantId(), is(1L));
    }

}

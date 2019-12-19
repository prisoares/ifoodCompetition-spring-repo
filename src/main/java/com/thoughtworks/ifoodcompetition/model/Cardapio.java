package com.thoughtworks.ifoodcompetition.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String prato;
    private String tipo;
    private String descricao;
    private String valor;
    private Long restaurantId;

    public Cardapio(Long id, String prato, String tipo, String descricao, String valor, Long restaurantId) {
        this.id = id;
        this.prato = prato;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.restaurantId = restaurantId;
    }

    public Long getId() { return id; }

    public String getPrato() { return prato; }

    public String getTipo() { return tipo; }

    public String getDescricao() { return descricao; }

    public String getValor() { return valor; }

    public Object getRestaurantId() { return restaurantId; }

    public void setRestaurantId(Long id) {
        this.restaurantId = id;
    }
}

package com.thoughtworks.ifoodcompetition.infraestructure;

import com.thoughtworks.ifoodcompetition.model.Cardapio;
import org.springframework.data.repository.Repository;

public interface CardapioRepository extends Repository<Cardapio, Long> {
    Cardapio save(Cardapio cardapio);

    Cardapio findById(Long id);

    void deleteById(Long id);
}

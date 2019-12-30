package com.thoughtworks.ifoodcompetition.infraestructure;

import com.thoughtworks.ifoodcompetition.model.Prato;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PratoRepository extends Repository<Prato, Long> {
    Prato save(Prato prato);

    Prato findByRestaurantIdAndId(Long idRestaurant, Long idPrato);

    Long deleteByRestaurantIdAndId(Long idRestaurant, Long idPrato);

    List<Prato> findByRestaurantIdEquals(Long idRestaurant);
}

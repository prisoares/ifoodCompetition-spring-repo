package com.thoughtworks.ifoodcompetition.infraestructure;

import com.thoughtworks.ifoodcompetition.model.Restaurant;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface RestaurantRepository extends Repository<Restaurant, Long> {

    Restaurant save(Restaurant restaurant);

    Restaurant findById(Long id);

    void deleteById(Long id);

    List<Restaurant> findAll();
}

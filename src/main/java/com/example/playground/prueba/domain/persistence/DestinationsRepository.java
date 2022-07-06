package com.example.playground.prueba.domain.persistence;

import com.example.playground.prueba.domain.model.entity.Destinations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DestinationsRepository extends JpaRepository<Destinations, Long> {
    Destinations findByNameAndCountry(String name, String country);
}

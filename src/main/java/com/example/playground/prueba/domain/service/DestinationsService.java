package com.example.playground.prueba.domain.service;

import com.example.playground.prueba.domain.model.entity.Destinations;

import java.util.List;

public interface DestinationsService {
    List<Destinations>getAll();
    Destinations getById(Long DestinationsId);
    Destinations create(Destinations destinations);
}

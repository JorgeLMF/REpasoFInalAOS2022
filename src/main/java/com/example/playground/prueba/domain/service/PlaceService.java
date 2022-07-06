package com.example.playground.prueba.domain.service;

import com.example.playground.prueba.domain.model.entity.Place;

import java.util.List;

public interface PlaceService {
    List<Place> getAll();
    Place getById(Long placeId);
    Place create(Place place, Long destinationsId);
    Place update(Long id, Place place, Long destinationsId);
}

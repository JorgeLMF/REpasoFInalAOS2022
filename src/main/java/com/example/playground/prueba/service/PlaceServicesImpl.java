package com.example.playground.prueba.service;

import com.example.playground.prueba.domain.model.entity.Place;
import com.example.playground.prueba.domain.persistence.DestinationsRepository;
import com.example.playground.prueba.domain.persistence.PlaceRepository;
import com.example.playground.prueba.domain.service.PlaceService;
import com.example.playground.shared.exception.ResourceNotFoundException;
import com.example.playground.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PlaceServicesImpl implements PlaceService {

    private final static String ENTITY = "Place";
    private final static String ENTITY2 = "Destinations";
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private DestinationsRepository destinationsRepository;
    @Autowired
    private Validator validator;
    @Override
    public List<Place> getAll() {
        return placeRepository.findAll();
    }

    @Override
    public Place getById(Long placeId) {
        return placeRepository.findById(placeId).orElseThrow(()-> new ResourceNotFoundException(ENTITY, placeId));
    }

    @Override
    public Place create(Place request, Long destinationsId) {
        var destination = destinationsRepository.findById(destinationsId);
        if (destination.isEmpty())
            throw new ResourceNotFoundException(ENTITY2, destinationsId);
        Set<ConstraintViolation<Place>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        var placeLocation = placeRepository.findByAltitudeAndLatitudeAndLongitude(request.getAltitude(), request.getLatitude(), request.getLongitude());
        if (placeLocation != null){
            throw new ResourceValidationException(ENTITY, "Place location already exists");
        }
        var placeNameLocation = placeRepository.findByNameAndAltitudeAndLatitudeAndLongitude(request.getName(),request.getAltitude(), request.getLatitude(), request.getLongitude());
        if (placeNameLocation != null){
            throw new ResourceValidationException(ENTITY, "Place name location already exists");
        }
        try {
            request.setDestination(destination.get());
            return placeRepository.save(request);
        }
        catch (Exception e){
            throw new ResourceValidationException(ENTITY, "An error occurred while saving place");
        }
    }

    @Override
    public Place update(Long id, Place request, Long destinationsId) {
        var destination = destinationsRepository.findById(destinationsId);
        if (destination.isEmpty())
            throw new ResourceNotFoundException(ENTITY2, destinationsId);
        Set<ConstraintViolation<Place>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        var placeLocation = placeRepository.findByAltitudeAndLatitudeAndLongitude(request.getAltitude(), request.getLatitude(), request.getLongitude());
        if (placeLocation != null){
            throw new ResourceValidationException(ENTITY, "Place location already exists");
        }
        var placeNameLocation = placeRepository.findByNameAndAltitudeAndLatitudeAndLongitude(request.getName(),request.getAltitude(), request.getLatitude(), request.getLongitude());
        if (placeNameLocation != null){
            throw new ResourceValidationException(ENTITY, "Place name location already exists");
        }
        try {
            return placeRepository.findById(id).map(
                    place ->
                            placeRepository.save(
                                    place.withName(request.getName())
                                            .withAltitude(request.getAltitude())
                                            .withLatitude(request.getLatitude())
                                            .withLongitude(request.getLongitude())
                                            .withHeritage(request.getHeritage())
                            )
            ).orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
        }
        catch (Exception e){
            throw new ResourceValidationException(ENTITY, "An error occurred while updating place");
        }
    }
}

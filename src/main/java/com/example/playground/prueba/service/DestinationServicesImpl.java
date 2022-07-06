package com.example.playground.prueba.service;

import com.example.playground.prueba.domain.model.entity.Destinations;
import com.example.playground.prueba.domain.persistence.DestinationsRepository;
import com.example.playground.prueba.domain.service.DestinationsService;
import com.example.playground.shared.exception.ResourceNotFoundException;
import com.example.playground.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class DestinationServicesImpl implements DestinationsService {

    private final static String ENTITY = "Destinations";
    @Autowired
    private DestinationsRepository destinationsRepository;
    @Autowired
    private Validator validator;
    @Override
    public List<Destinations> getAll() {
        return destinationsRepository.findAll();
    }

    @Override
    public Destinations getById(Long DestinationsId) {
        return destinationsRepository.findById(DestinationsId).orElseThrow(()-> new ResourceNotFoundException(ENTITY, DestinationsId));
    }

    @Override
    public Destinations create(Destinations request) {
        Set<ConstraintViolation<Destinations>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        var destinations = destinationsRepository.findByNameAndCountry(request.getName(), request.getCountry());
        if (destinations != null) {
            throw new ResourceValidationException(ENTITY, "Destination combination of name and country already exists");
        }
        try{
            return destinationsRepository.save(request);
        }catch (Exception e){
            throw new ResourceValidationException(ENTITY, "An error occurred while saving destinations");
        }
    }
}

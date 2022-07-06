package com.example.playground.prueba.mapping;

import com.example.playground.prueba.domain.model.entity.Destinations;
import com.example.playground.prueba.resource.CreateDestinationResource;
import com.example.playground.prueba.resource.DestinationResource;
import com.example.playground.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class DestinationsMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public DestinationResource toResource(Destinations model){
        return mapper.map(model, DestinationResource.class);
    }

    public List<DestinationResource> toResource(List<Destinations> model){
        return mapper.mapList(model,DestinationResource.class);
    }

    public Destinations toModel(CreateDestinationResource resource){
        return mapper.map(resource, Destinations.class);
    }
}
package com.example.playground.prueba.mapping;


import com.example.playground.prueba.domain.model.entity.Place;
import com.example.playground.prueba.resource.CreatePlaceResource;
import com.example.playground.prueba.resource.PlaceResource;
import com.example.playground.prueba.resource.UpdatePlaceResource;
import com.example.playground.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class PlaceMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public PlaceResource toResource(Place model){
        return mapper.map(model, PlaceResource.class);
    }

    public List<PlaceResource> toResource(List<Place> model){
        return mapper.mapList(model, PlaceResource.class);
    }

    public Place toModel(CreatePlaceResource resource){
        return mapper.map(resource, Place.class);
    }

    public Place toModel(UpdatePlaceResource resource){
        return mapper.map(resource, Place.class);
    }
}

package com.example.playground.prueba.resource;

import com.example.playground.prueba.domain.model.numeration.Heritage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceResource {

    private Long id;

    private String name;

    private Long altitude;

    private Long latitude;

    private Long longitude;

    private Heritage heritage;

    private DestinationResource destination;
}

package com.example.playground.prueba.resource;

import com.example.playground.prueba.domain.model.numeration.Heritage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreatePlaceResource {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private Long altitude;

    @NotNull
    private Long latitude;

    @NotNull
    private Long longitude;

    @Enumerated(EnumType.ORDINAL)
    private Heritage heritage;
}

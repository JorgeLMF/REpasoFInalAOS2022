package com.example.playground.prueba.domain.persistence;

import com.example.playground.prueba.domain.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByAltitudeAndLatitudeAndLongitude(Long altitude, Long latitude, Long longitude);
    Place findByNameAndAltitudeAndLatitudeAndLongitude(String name, Long altitude, Long latitude, Long longitude);




}

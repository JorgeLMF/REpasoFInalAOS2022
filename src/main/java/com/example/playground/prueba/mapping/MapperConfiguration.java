package com.example.playground.prueba.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("playgroundMappingConfiguration")
public class MapperConfiguration {
  @Bean
  public DestinationsMapper destinationsMapper(){
      return new DestinationsMapper();
  }

  @Bean
  public PlaceMapper placeMapper(){
      return new PlaceMapper();
  }
}

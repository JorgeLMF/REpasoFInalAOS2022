package com.example.playground.prueba.api;

import com.example.playground.prueba.domain.service.DestinationsService;
import com.example.playground.prueba.mapping.DestinationsMapper;
import com.example.playground.prueba.resource.CreateDestinationResource;
import com.example.playground.prueba.resource.DestinationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Destination")
@RestController
@RequestMapping("api/v1/destinations")
public class DestinationController {
    @Autowired
    private DestinationsService destinationsService;
    @Autowired
    private DestinationsMapper destinationsMapper;

    @Operation(summary = "get Destinations", description = "get alt Destinations")
    @GetMapping
    @ApiResponses
    (value = {
       @ApiResponse(responseCode = "200",description = "Get all Destinations successfully"),
       @ApiResponse(responseCode = "400",description = "Error getting all Destinations")})
    public List<DestinationResource> getAllDestinations(){
        return destinationsMapper.toResource(destinationsService.getAll());
    }
    @Operation(summary = "get Destination by id", description = "get Destination by id")
    @GetMapping("{id}")
    @ApiResponses
    (value = {
       @ApiResponse(responseCode = "200",description = "Get Destination by Id successfully"),
       @ApiResponse(responseCode = "400",description = "Error getting Destination by Id") })
    public DestinationResource getDestinationsById(@PathVariable Long id){
       return destinationsMapper.toResource(destinationsService.getById(id));
    }

    @Operation(summary = "create new Destination", description = "create new Destination")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Destination created successfully"),
            @ApiResponse(responseCode = "400",description = "Error creating Destination") })
    public DestinationResource createDestination(@RequestBody CreateDestinationResource resource){
        return destinationsMapper.toResource(destinationsService.create(destinationsMapper.toModel(resource)));
    }
}

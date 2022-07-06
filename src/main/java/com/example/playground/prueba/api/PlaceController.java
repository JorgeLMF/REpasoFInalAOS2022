package com.example.playground.prueba.api;

import com.example.playground.prueba.domain.service.PlaceService;
import com.example.playground.prueba.mapping.PlaceMapper;
import com.example.playground.prueba.resource.CreatePlaceResource;
import com.example.playground.prueba.resource.PlaceResource;
import com.example.playground.prueba.resource.UpdatePlaceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Place")
@RestController
@RequestMapping("api/v1/")
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceMapper placeMapper;
    @Operation(summary = "get alt Places", description = "get alt Places")
    @GetMapping("places")
    @ApiResponses(value = {
       @ApiResponse(responseCode = "200",description = "Get all Places successfully"),
       @ApiResponse(responseCode = "400",description = "Error getting all Places") })
    public List<PlaceResource> getAllPlaces(){
        return placeMapper.toResource(placeService.getAll());
    }
    @Operation(summary = "get Place by id", description = "get Place by id")
    @GetMapping("destinations/{destinationId}/places/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Place by id successfully"),
            @ApiResponse(responseCode = "400",description = "Error getting Place by id") })
    public PlaceResource getPlaceById(@PathVariable Long id){
        return placeMapper.toResource(placeService.getById(id));
    }

    @Operation(summary = "create place", description = "create place")
    @PostMapping("destinations/{destinationId}/places")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Post Place successfully"),
            @ApiResponse(responseCode = "400",description = "Error posting Place") })
    public PlaceResource createPlace(@RequestBody CreatePlaceResource model, @PathVariable Long destinationId){
        return placeMapper.toResource(placeService.create(placeMapper.toModel(model), destinationId));
    }

    @Operation(summary = "update place", description = "update place")
    @PutMapping("destinations/{destinationId}/places/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Put Place successfully"),
            @ApiResponse(responseCode = "400",description = "Error putting Place") })
    public PlaceResource updatePlace(@PathVariable Long id, @RequestBody UpdatePlaceResource model, @PathVariable Long destinationId){
        return placeMapper.toResource(placeService.update(id,placeMapper.toModel(model),destinationId));
    }

}

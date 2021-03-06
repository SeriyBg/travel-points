package com.github.seriybg.travelpoints.geospatial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GeospatialController {

    @Autowired
    private GeospatialService service;

    public Mono<TravelLocation> saveNewLocation(Mono<TravelLocation> location) {
        return location.doOnSuccess(service::insert);
    }
}

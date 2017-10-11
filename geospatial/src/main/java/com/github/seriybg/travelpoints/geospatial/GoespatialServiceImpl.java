package com.github.seriybg.travelpoints.geospatial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import reactor.core.publisher.Flux;

public class GoespatialServiceImpl implements GeospatialServiceCustom {

    @Autowired
    private GeospatialService geospatialService;

    @Override
    public Flux<TravelLocation> findByPointNear(GeoJsonPoint location, Distance distance) {
        return Flux.empty();
    }
}

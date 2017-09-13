package com.github.seriybg.travelpoints.geospatial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class GoespatialServiceImpl implements GeospatialServiceCustom {

    @Autowired
    private GeospatialService geospatialService;

    @Override
    public Flux<TravelLocation> findByLocationNear(TravelLocation location, Distance distance) {
        return Flux.empty();
    }
}

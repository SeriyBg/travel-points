package com.github.seriybg.travelpoints.geospatial;

import org.springframework.data.geo.Distance;
import reactor.core.publisher.Flux;

public interface GeospatialServiceCustom {
    Flux<TravelLocation> findByLocationNear(TravelLocation location, Distance distance);
}

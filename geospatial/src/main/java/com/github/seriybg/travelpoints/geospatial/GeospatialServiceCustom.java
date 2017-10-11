package com.github.seriybg.travelpoints.geospatial;

import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import reactor.core.publisher.Flux;

public interface GeospatialServiceCustom {
    Flux<TravelLocation> findByPointNear(GeoJsonPoint point, Distance distance);
}

package com.github.seriybg.travelpoints.geospatial;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GeospatialService extends ReactiveMongoRepository<TravelLocation, String> {
}

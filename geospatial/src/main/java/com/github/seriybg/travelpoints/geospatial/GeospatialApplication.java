package com.github.seriybg.travelpoints.geospatial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class GeospatialApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeospatialApplication.class, args);
	}
}

package com.github.seriybg.travelpoints.geospatial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class GeospatialApplication {

	@Autowired
	private GeospatialService service;

	public static void main(String[] args) {
		SpringApplication.run(GeospatialApplication.class, args);
	}
}

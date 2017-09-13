package com.github.seriybg.travelpoints.geospatial;

import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = GeospatialServiceTest.Initializer.class)
public class GeospatialServiceTest {

    @ClassRule
    public static GenericContainer mongodb =
            new GenericContainer("mongo:latest")
                    .withExposedPorts(27017);

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongodb.getContainerIpAddress(),
                    "spring.data.mongodb.port=" + mongodb.getMappedPort(27017))
                    .applyTo(configurableApplicationContext);
        }
    }

    @Autowired
    private GeospatialService service;

    @Test
    public void shouldCreateNewTravelLocation() throws Exception {
        final TravelLocation entity =
                new TravelLocation("123", "name", "description", new Point(1L, 2L));
        final Mono<TravelLocation> savedLocation = service.save(entity);

        StepVerifier.create(savedLocation)
                .expectNext(new TravelLocation("123", "name", "description", new Point(1L, 2L)))
                .verifyComplete();
    }

    @Test
    public void shouldFindNearestLocations() throws Exception {
        final TravelLocation location =
                new TravelLocation("123", "location", "description", new Point(1L, 1L));
        List<TravelLocation> locations = new ArrayList<>();
        locations.add(location);
        locations.add(new TravelLocation("nearLocation1", "nearLocation1", "description", new Point(1L, 2L)));
        locations.add(new TravelLocation("nearLocation2", "nearLocation2", "description", new Point(2L, 2L)));
        locations.add(new TravelLocation("farAwayLocation", "farAwayLocation", "description", new Point(1024L, 1024L)));

        locations.forEach(service::save);

        final Flux<TravelLocation> nearestLocations = service.findByLocationNear(location, new Distance(5, Metrics.KILOMETERS));

        StepVerifier.create(nearestLocations)
                .expectNext(
                        new TravelLocation("nearLocation1", "nearLocation1", "description", new Point(1L, 2L)),
                        new TravelLocation("nearLocation2", "nearLocation2", "description", new Point(2L, 2L)))
                .verifyComplete();
    }
}

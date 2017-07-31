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
import org.springframework.data.geo.Point;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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

    @Test(timeout = 10000L)
    public void shouldCreateNewTravelLocation() throws Exception {
        final TravelLocation entity =
                new TravelLocation("123", "name", "description", new Point(1L, 2L));
        final Mono<TravelLocation> savedLocation = service.save(entity);

        StepVerifier.create(savedLocation)
                .expectNext(new TravelLocation("123", "name", "description", new Point(1L, 2L)))
                .verifyComplete();
    }
}

package com.github.seriybg.travelpoints.geospatial;

import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = GeospatialApplicationTests.Initializer.class)
public class GeospatialApplicationTests {

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

	@Test
	public void contextLoads() {
	}
}

package com.github.seriybg.travelpoints.geospatial;

import org.junit.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import static org.assertj.core.api.Assertions.assertThat;

public class TravelLocationTest {

    @Test
    public void shouldBeEqualIfAreIdentical() throws Exception {
        TravelLocation pointA =
                new TravelLocation("id", "name", "description", new GeoJsonPoint(123L, 321L));
        TravelLocation pointB =
                new TravelLocation("id", "name", "description", new GeoJsonPoint(123L, 321L));

        assertThat(pointA).isEqualTo(pointB);
        assertThat(pointA.hashCode()).isEqualTo(pointB.hashCode());
    }

    @Test
    public void shouldNotBeEqualIfAreDifferent() throws Exception {
        TravelLocation pointA =
                new TravelLocation("id", "name", "description", new GeoJsonPoint(123L, 321L));
        TravelLocation pointB =
                new TravelLocation("id1", "another", "no data", new GeoJsonPoint(432L, 1L));

        assertThat(pointA).isNotEqualTo(pointB);
        assertThat(pointA.hashCode()).isNotEqualTo(pointB.hashCode());
    }
}

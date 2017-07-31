package com.github.seriybg.travelpoints.geospatial;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document
@AllArgsConstructor
@EqualsAndHashCode()
@ToString
public class TravelLocation implements GeoJson<List<Double>> {

    private static final String TYPE = "Feature";

    @Id
    @Getter
    private String id;

    @Getter
    private String name;

    @Getter
    private String description;

    private Point location;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public List<Double> getCoordinates() {
        return Arrays.asList(location.getX(), location.getY());
    }
}

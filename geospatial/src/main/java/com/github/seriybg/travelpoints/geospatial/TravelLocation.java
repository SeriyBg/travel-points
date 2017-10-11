package com.github.seriybg.travelpoints.geospatial;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "travelLocation")
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TravelLocation {

    @Id
    @Getter
    private String id;

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private final GeoJsonPoint point;
}

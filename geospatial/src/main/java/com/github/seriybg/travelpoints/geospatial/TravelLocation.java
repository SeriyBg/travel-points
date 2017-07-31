package com.github.seriybg.travelpoints.geospatial;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TravelLocation {

    @Id
    @Getter
    private String id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private Point location;

}

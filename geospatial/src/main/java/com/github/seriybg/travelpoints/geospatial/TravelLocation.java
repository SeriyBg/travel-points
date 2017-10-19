package com.github.seriybg.travelpoints.geospatial;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "travelLocation")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
@CompoundIndexes({@CompoundIndex(def = "{'point':'2dsphere'}")})
public class TravelLocation {

    @Id
    @Getter
    private String id;

    @Getter
    @NonNull
    private String name;

    @Getter
    @NonNull
    private String description;

    @Getter
    @Indexed
    @NonNull
    private GeoJsonPoint point;
}

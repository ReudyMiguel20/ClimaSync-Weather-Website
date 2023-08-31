package com.climasync.weather.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// Represents a location (e.g. "London, UK") and its coordinates (latitude and longitude)
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String country;
    private double lat;
    private double lon;

    public Location(String name, String country, double lat, double lon) {
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }
}

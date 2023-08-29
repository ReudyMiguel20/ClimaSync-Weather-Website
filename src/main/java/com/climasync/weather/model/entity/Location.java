package com.climasync.weather.model.entity;

import com.climasync.weather.model.dto.WeatherCondition;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// Represents a location (e.g. "London, UK") and its coordinates (latitude and longitude)
@Document
@Data
@NoArgsConstructor
public class Location {

    @Id
    private String id;
    @Indexed(unique = true)
    private String place;
    private String country;
    private double latitude;
    private double longitude;

    public Location(String place, String country, double latitude, double longitude) {
        this.place = place;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

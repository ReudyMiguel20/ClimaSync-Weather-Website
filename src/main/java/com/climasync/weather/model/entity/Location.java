package com.climasync.weather.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private String id;
    private String name;
    private String country;
    @JsonProperty("country_code")
    private String countryCode;
//    @Indexed(unique = true)
    private double lat;
//    @Indexed(unique = true)
    private double lon;

    public Location(String name, String country, double lat, double lon) {
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }
}

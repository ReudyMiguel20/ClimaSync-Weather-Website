package com.climasync.weather.model.entity;

import com.climasync.weather.model.dto.WeatherCondition;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Location {

    private String place;
    private String country;
    private double latitude;
    private double longitude;
    private WeatherCondition weatherCondition;

}

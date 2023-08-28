package com.climasync.weather.model.dto;

import lombok.Builder;
import lombok.Data;


// Represents the weather condition of a location at a given time (e.g. "Clear sky", "Clouds", "Rain", etc.)
@Data
@Builder
public class WeatherCondition {

    private String mainCondition;
    private String description;

}

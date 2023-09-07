package com.climasync.weather.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// Represents the weather condition of a location at a given time (e.g. "Clear sky", "Clouds", "Rain", etc.)
// This part is grabbed from the part 'weather' in the API request from the OpenWeatherAPI
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherCondition {

    @JsonProperty("main")
    private String mainCondition;

    @JsonProperty("description")
    private String description;

}

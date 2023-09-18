package com.climasync.weather.model.entity;

import com.climasync.weather.model.dto.WeatherCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentWeather {

    @Id
    @JsonIgnore
    private String id;


    private Location location;
    private WeatherCondition weatherCondition;

    // This part is found in the 'main' part of the JSON request from OpenWeatherAPI
    @JsonProperty("temp")
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    @JsonProperty("temp_min")
    private double minTemperature;
    @JsonProperty("temp_max")
    private double maxTemperature;
    @JsonProperty("pressure")
    private int pressure;
    @JsonProperty("humidity")
    private int humidity;
    // -------------------------------------------------------------------------------

    private double windSpeed;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    @JsonIgnore
    private LocalDateTime timestamp;

    public CurrentWeather(Location location, WeatherCondition weatherCondition, double temp, double feelsLike, double minTemperature,
                          double maxTemperature, Integer pressure, Integer humidity, double windSpeed, LocalDateTime timestamp) {
        this.location = location;
        this.weatherCondition = weatherCondition;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.timestamp = timestamp;
    }
}

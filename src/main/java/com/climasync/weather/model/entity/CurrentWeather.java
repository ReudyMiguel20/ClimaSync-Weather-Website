package com.climasync.weather.model.entity;

import com.climasync.weather.model.dto.WeatherCondition;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class CurrentWeather {

    @Id
    private String id;
    @Indexed(unique = true)
    private Location location;
    private WeatherCondition weatherCondition;
    private double temperature;
    private double minTemperature;
    private double maxTemperature;
    private double humidity;
    private double windSpeed;
    private LocalDateTime timestamp;

    public CurrentWeather(WeatherCondition weatherCondition, double temperature, double minTemperature,
                          double maxTemperature, double humidity, double windSpeed, LocalDateTime timestamp) {
        this.weatherCondition = weatherCondition;
        this.temperature = temperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.timestamp = timestamp;
    }
}

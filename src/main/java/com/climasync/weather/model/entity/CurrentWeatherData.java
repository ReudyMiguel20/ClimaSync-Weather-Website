package com.climasync.weather.model.entity;

import com.climasync.weather.model.dto.WeatherCondition;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class CurrentWeatherData {

    private WeatherCondition weatherCondition;
    private double temperature;
    private double minTemperature;
    private double maxTemperature;
    private double humidity;
    private double windSpeed;
    private LocalDateTime timestamp;

}

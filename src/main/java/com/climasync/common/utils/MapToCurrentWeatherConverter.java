package com.climasync.common.utils;

import com.climasync.weather.model.entity.CurrentWeather;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

// This class is used to convert a Map to a CurrentWeather object using the ModelMapper library
@Component
public class MapToCurrentWeatherConverter extends AbstractConverter<Map<String, Object>, CurrentWeather> {

    // Convert a Map to a CurrentWeather object using the ModelMapper library
    @Override
    public CurrentWeather convert(Map<String, Object> source) {
        CurrentWeather currentWeather = new CurrentWeather();

        // Map the values from the Map to the CurrentWeather fields with name differences
        currentWeather.setTemp((Double) source.get("temp"));
        currentWeather.setFeelsLike((Double) source.get("feels_like"));
        currentWeather.setMinTemperature((Double) source.get("temp_min"));
        currentWeather.setMaxTemperature((Double) source.get("temp_max"));
        currentWeather.setPressure((Integer) source.get("pressure"));
        currentWeather.setHumidity((Integer) source.get("humidity"));

        return currentWeather;
    }
}

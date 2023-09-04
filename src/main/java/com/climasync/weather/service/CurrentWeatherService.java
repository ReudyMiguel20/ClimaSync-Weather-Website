package com.climasync.weather.service;


import com.climasync.weather.model.entity.CurrentWeather;
import com.climasync.weather.model.entity.Location;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CurrentWeatherService {
    CurrentWeather getCurrentWeatherFromExternalApi(Location location) throws JsonProcessingException;
}

package com.climasync.weather.service;

import com.climasync.weather.model.entity.CachedWeather;
import com.climasync.weather.model.entity.CurrentWeather;
import com.climasync.weather.model.entity.Location;

public interface CachedWeatherService  {

    CachedWeather findByLocation(Location location);

    CurrentWeather saveForecastToCache(CurrentWeather currentWeather);
}

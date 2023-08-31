package com.climasync.weather.service;

import com.climasync.weather.model.entity.CachedWeather;
import com.climasync.weather.model.entity.Location;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Repository;

public interface CachedWeatherService  {

    CachedWeather findByLocation(Location location);
}

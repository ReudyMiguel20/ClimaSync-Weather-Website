package com.climasync.weather.service.impl;

import com.climasync.weather.model.entity.CachedWeather;
import com.climasync.weather.model.entity.CurrentWeather;
import com.climasync.weather.model.entity.Location;
import com.climasync.weather.repository.CachedWeatherRepository;
import com.climasync.weather.service.CachedWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CachedWeatherImpl implements CachedWeatherService {

    private final CachedWeatherRepository cachedWeatherRepository;

    @Override
    public CachedWeather findByLocation(Location location) {
        return cachedWeatherRepository.findByLocation(location).orElse(null);
    }

    @Override
    public CurrentWeather saveForecastToCache(CurrentWeather currentWeather) {
        CachedWeather cachedWeather = CachedWeather.builder()
                .location(currentWeather.getLocation())
                .data(currentWeather)
                .timestamp(LocalDateTime.now())
                .build();

        cachedWeatherRepository.save(cachedWeather);

        return currentWeather;
    }


}

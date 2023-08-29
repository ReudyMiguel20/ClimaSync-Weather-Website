package com.climasync.weather.service.impl;

import com.climasync.weather.repository.CachedWeatherRepository;
import com.climasync.weather.service.CachedWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CachedWeatherImpl implements CachedWeatherService {

    private final CachedWeatherRepository cachedWeatherRepository;



}

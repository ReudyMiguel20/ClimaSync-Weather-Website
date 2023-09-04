package com.climasync.weather.service.impl;

import com.climasync.weather.model.entity.CurrentWeather;
import com.climasync.weather.model.entity.Location;
import com.climasync.weather.repository.CurrentWeatherRepository;
import com.climasync.weather.service.CachedWeatherService;
import com.climasync.weather.service.CurrentWeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrentWeatherImpl implements CurrentWeatherService {

    private final CurrentWeatherRepository currentWeatherRepository;
    private final CachedWeatherService cachedWeatherService;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final String getOpenWeatherApiKey;
    private final String getCurrentWeatherDataUri;


    /**
     *
     * @param location - Location object to search for in the database, if the location doesn't exist in the database,
     *                 it will search for it in the OpenWeatherMap API and return a CurrentWeather object
     *                 if the place and country exists in the API.
     *
     * @return - CurrentWeather object or throw an exception if the location doesn't exist in the database or in the API
     *
     * @throws JsonProcessingException - If the JSON response from the API can't be converted to a CurrentWeather object7
     */
    public CurrentWeather getCurrentWeatherForLocation(Location location) throws JsonProcessingException {
        CurrentWeather weatherForLocation = cachedWeatherService.findByLocation(location).getData();

        if (weatherForLocation == null) {
            return getCurrentWeatherFromExternalApi(location);
        }

        return weatherForLocation;
    }

    @Override
    public CurrentWeather getCurrentWeatherFromExternalApi(Location location) throws JsonProcessingException {
        String locationInfo = String.format("?lat=%f&lon=%f&units=metric&appid=%s", location.getLat(), location.getLon(), getOpenWeatherApiKey);
        String urlRequest = getCurrentWeatherDataUri + locationInfo;

        // Get the JSON response from the API
        Mono<String> jsonResponseMono = webClient.get()
                .uri(urlRequest)
                .retrieve()
                .bodyToMono(String.class);

        // Convert the JSON response to a CurrentWeather object
        String jsonResponse = jsonResponseMono.block();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        CurrentWeather currentWeatherForLocation = objectMapper.readValue(jsonResponse, CurrentWeather.class);

        // Save the CurrentWeather object to the database
        cachedWeatherService.saveForecastToCache(currentWeatherForLocation);

        return currentWeatherForLocation;
    }

}

package com.climasync.weather.service.impl;


import com.climasync.weather.model.dto.WeatherCondition;
import com.climasync.weather.model.entity.CurrentWeather;
import com.climasync.weather.model.entity.Location;
import com.climasync.weather.repository.CurrentWeatherRepository;
import com.climasync.weather.service.CachedWeatherService;
import com.climasync.weather.service.CurrentWeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrentWeatherImpl implements CurrentWeatherService {

    private final CurrentWeatherRepository currentWeatherRepository;
    private final CachedWeatherService cachedWeatherService;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
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
    @Override
    public CurrentWeather getCurrentWeatherForLocation(Location location) throws JsonProcessingException {
        CurrentWeather weatherForLocation = currentWeatherRepository.findByLocation(location).orElse(null);

        if (weatherForLocation == null) {
            return getCurrentWeatherFromExternalApi(location);
        }

        return weatherForLocation;
    }

    @Override
    public CurrentWeather getCurrentWeatherFromExternalApi(Location location) throws JsonProcessingException {
        String locationInfo = String.format("?lat=%f&lon=%f&units=metric&appid=%s", location.getLat(), location.getLon(), getOpenWeatherApiKey);
        String urlRequest = getCurrentWeatherDataUri + locationInfo;

        // Get the JSON response from the API as a String
        Mono<String> jsonResponseMono = webClient.get()
                .uri(urlRequest)
                .retrieve()
                .bodyToMono(String.class);

        // Convert the JSON response to a CurrentWeather object
        String jsonResponse = jsonResponseMono.block();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        CurrentWeather currentWeatherForLocation = objectMapper.readValue(jsonResponse, CurrentWeather.class);

        // Parse the Json into a map
        Map<String, Object> jsonMap = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {});

        // Access the weather array from the map
        List<Map<String, Object>> weatherArray = (List<Map<String, Object>>) jsonMap.get("weather");
//
//        System.out.println("Weather Data:");
//        for (Map<String, Object> weatherData: weatherArray) {
//            System.out.println("Main: " + weatherData.get("main"));
//            System.out.println("Description " + weatherData.get("description"));
//        }

        // Access the "main" object from the Map
        Map<String, Object> mainObject = (Map<String, Object>) jsonMap.get("main");

        System.out.println("\nMain Weather Data:");
        for (Map.Entry<String, Object> entry : mainObject.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + ": " + value);
        }

        // (Considering putting all three methods into one to modify the CurrentWeather object)

        // Assign the location to the CurrentWeather object
        assignLocationToCurrentWeather(location, currentWeatherForLocation);

        // Method that creates a 'WeatherCondition' object and assigns it to the 'CurrentWeather' object
        assignWeatherConditionToCurrentWeather(weatherArray, currentWeatherForLocation);

        // Assign the temperature values to the 'CurrentWeather' object
        assignTemperatureValuesToCurrentWeather(mainObject, currentWeatherForLocation);

        // Save the CurrentWeather object to the database and return it
        saveForecast(currentWeatherForLocation);
        return currentWeatherForLocation;
    }

    @Override
    public CurrentWeather saveForecast(CurrentWeather currentWeather) {
        return currentWeatherRepository.save(currentWeather);
    }

    public void assignWeatherConditionToCurrentWeather(List<Map<String, Object>> weather, CurrentWeather currentWeather) {
        // Create a WeatherCondition object from the List on weather, which comes from the API call
        for (Map<String, Object> condition : weather) {
            WeatherCondition weatherCondition = new WeatherCondition().builder()
                    .mainCondition(String.valueOf(condition.get("main")))
                    .description(String.valueOf(condition.get("description")))
                    .build();

            currentWeather.setWeatherCondition(weatherCondition);
        }
    }

    public void assignLocationToCurrentWeather(Location location, CurrentWeather currentWeather) {
        currentWeather.setLocation(location);
    }

    public void assignTemperatureValuesToCurrentWeather(Map<String, Object> mainValues, CurrentWeather currentWeather) throws JsonProcessingException {
//        modelMapper.addMappings(new CustomTempPropertyMap());

        //Can use modelmapper to map the 'Map' to a currentWeather object
//        CurrentWeather currentWeatherWithOnlyTemps = WeatherMapper.mapToCurrentWeather()

        //Can use Apache BeanUtils to fill the values from one object to another
        for (Map.Entry<String, Object> values : mainValues.entrySet()) {

        }
    }

}

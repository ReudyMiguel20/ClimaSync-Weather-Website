package com.climasync.weather.service.impl;


import com.climasync.common.utils.MapToCurrentWeatherConverter;
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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.climasync.common.utils.Utils.copyNonNullProperties;

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
    private final MapToCurrentWeatherConverter converter;


    /**
     * @param location - Location object to search for in the database, if the location doesn't exist in the database,
     *                 it will search for it in the OpenWeatherMap API and return a CurrentWeather object
     *                 if the place and country exists in the API.
     * @return - CurrentWeather object or throw an exception if the location doesn't exist in the database or in the API
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

    /**
     * Method that makes a request to the OpenWeatherMap API and returns a CurrentWeather object with the data from the API.
     * It also saves the CurrentWeather object to the database.
     *
     * @param location - Location object to search for in the OpenWeatherMap API
     * @return - CurrentWeather object with the data from the API
     * @throws JsonProcessingException - If the JSON response from the API can't be converted to a CurrentWeather object
     */
    @Override
    public CurrentWeather getCurrentWeatherFromExternalApi(Location location) throws JsonProcessingException {
        // Make the request to the OpenWeather API
        String locationInfo = String.format("?lat=%f&lon=%f&units=metric&appid=%s", location.getLat(), location.getLon(), getOpenWeatherApiKey);
        String urlRequest = getCurrentWeatherDataUri + locationInfo;


        // Get the JSON response from the API as a String
        Mono<String> jsonResponseMono = webClient.get()
                .uri(urlRequest)
                .retrieve()
                .bodyToMono(String.class);

        // Convert the JSON response to a CurrentWeather object
        String jsonResponse = jsonResponseMono.block();

        // Configure the ObjectMapper to ignore unknown properties and ignored properties from the JSON response
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        // Register the JavaTimeModule to the ObjectMapper to be able to parse the 'dt' value from the JSON response
        objectMapper.registerModule(new JavaTimeModule());

        // Parse the Json into a map to access the weather array and the main array separately
        Map<String, Object> jsonMap = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {
        });


        // Access the 'weather' array from the map
        List<Map<String, Object>> weatherArray = (List<Map<String, Object>>) jsonMap.get("weather");

        // Access the 'main' array from the Map
        Map<String, Object> mainArray = (Map<String, Object>) jsonMap.get("main");

        //Create and assigns the respective values for the 'CurrentWeather' object
        CurrentWeather currentWeatherForLocation = createCurrentWeatherForecastForLocation(location, weatherArray, mainArray);


        // Save the CurrentWeather object to the database and return it
        saveForecast(currentWeatherForLocation);
        return currentWeatherForLocation;
    }

    @Override
    public CurrentWeather saveForecast(CurrentWeather currentWeather) {
        return currentWeatherRepository.save(currentWeather);
    }

    // Method that creates and assigns the respective values for the 'CurrentWeather' object and returns it
    public CurrentWeather createCurrentWeatherForecastForLocation(Location location, List<Map<String, Object>> weatherArray, Map<String, Object> mainArray) throws JsonProcessingException {
        CurrentWeather currentWeatherForLocation = new CurrentWeather();

        // Assign the location to the CurrentWeather object
        assignLocationToCurrentWeather(location, currentWeatherForLocation);

        // Method that creates a 'WeatherCondition' object and assigns it to the 'CurrentWeather' object
        assignWeatherConditionToCurrentWeather(weatherArray, currentWeatherForLocation);

        // Assign the temperature values and the Date to the 'CurrentWeather' object
        assignTemperatureValuesToCurrentWeather(mainArray, currentWeatherForLocation);
        currentWeatherForLocation.setTimestamp(LocalDateTime.now());

        return currentWeatherForLocation;
    }

    // Method that creates a 'WeatherCondition' object and assigns it to the 'CurrentWeather' object
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

    // Method that assigns the location to the 'CurrentWeather' object
    public void assignLocationToCurrentWeather(Location location, CurrentWeather currentWeather) {
        currentWeather.setLocation(location);
    }

    // Method that copies the temperature values from the 'main' array to the 'CurrentWeather' object
    public void assignTemperatureValuesToCurrentWeather(Map<String, Object> mainValues, CurrentWeather currentWeather) throws JsonProcessingException {
        // Create a CurrentWeather object with only the temperature values
        CurrentWeather currentWeatherWithOnlyTemps = converter.convert(mainValues);

        // Copy the temperature values to the CurrentWeather object
        copyNonNullProperties(currentWeatherWithOnlyTemps, currentWeather);
    }

}

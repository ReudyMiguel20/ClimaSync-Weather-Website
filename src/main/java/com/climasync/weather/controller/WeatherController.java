package com.climasync.weather.controller;

import com.climasync.user.model.entity.User;
import com.climasync.user.service.UserService;
import com.climasync.weather.model.entity.CurrentWeather;
import com.climasync.weather.model.entity.Location;
import com.climasync.weather.service.CachedWeatherService;
import com.climasync.weather.service.CurrentWeatherService;
import com.climasync.weather.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:4200/dashboard")
public class WeatherController {

    private final CurrentWeatherService currentWeatherService;
    private final LocationService locationService;
    private final UserService userService;
    private final CachedWeatherService cachedWeatherService;
    private final ModelMapper modelMapper;

    /**
     * HTTP GET request to get the current weather for a location by its name and country code (e.g. "US")
     *
     * @param name    -- Location name (city, state) to search for
     * @param country -- Country code (e.g. "US") to search for
     * @return
     */
    @GetMapping("/current")
    public ResponseEntity<CurrentWeather> getCurrentWeatherByPlaceAndCountry(Authentication auth, @RequestParam(name = "q") String name, @RequestParam(name = "country") String country) throws JsonProcessingException {
        Location location = locationService.getLocationByNameAndCountry(name, country);
        CurrentWeather currentWeatherByPlaceAndCountry = currentWeatherService.getCurrentWeatherForLocation(location);

        // Save the current weather to the user's history
        String requestUserEmail = auth.getName();
        User requestUser = userService.retrieveUserByEmail(requestUserEmail);
        userService.saveCurrentWeatherToUserHistory(requestUser, currentWeatherByPlaceAndCountry);

        return ResponseEntity.ok(currentWeatherByPlaceAndCountry);
    }

    @GetMapping("/location")
    public ResponseEntity<Location> getLocationByPlaceAndCountry(@RequestParam(name = "q") String name, @RequestParam(name = "country") String country) throws JsonProcessingException {
        Location location = locationService.getLocationByNameAndCountry(name, country);

        return ResponseEntity.ok(location);
    }

}

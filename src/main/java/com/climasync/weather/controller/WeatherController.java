package com.climasync.weather.controller;

import com.climasync.weather.service.CurrentWeatherService;
import com.climasync.weather.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {

    private final CurrentWeatherService currentWeatherService;
    private final LocationService locationService;

    /**
     *
     * @param place -- Location name (city, state) to search for
     * @param country -- Country code (e.g. "US") to search for
     * @return
     */
//    @GetMapping("/current")
//    public ResponseEntity<CurrentWeatherData> getCurrentWeatherByPlaceAndCountry(@RequestParam (name = "q") String place, @RequestParam (name = "country") String country) {
//        CurrentWeatherData currentWeatherData =
//    }

}

package com.climasync.weather.service.impl;

import com.climasync.weather.exception.LocationNotFoundException;
import com.climasync.weather.model.entity.Location;
import com.climasync.weather.repository.LocationRepository;
import com.climasync.weather.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.climasync.common.utils.Utils.getCountryCode;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final ObjectMapper objectMapper;
    private final WebClient webClient;
    private final String getOpenWeatherApiKey;
    private final String getGeolocationWeatherApiUri;



    @Override
    public Location saveLocation(Location location) {
       return locationRepository.save(location);
    }

    /**
     * This method will search for a location in the database and if it doesn't exist, it will search for it in the
     * OpenWeatherMap API and return a Location object if the place and country exists in the API.
     *
     * @param name - Location name (city, state) to search for
     * @param country - Country name to search for, it will be converted to two-letter country code later on
     * @return - Location object or throw an exception if the location doesn't exist in the database or in the API
     * @throws JsonProcessingException
     */
    @Override
    public Location getLocationByNameAndCountry(String name, String country) throws JsonProcessingException {
        Location locationToReturn = locationRepository.findByNameAndCountry(name, country)
                .orElse(null);

        if (locationToReturn == null) {
            locationToReturn = getLocationFromExternalApi(name, country);
        }

        return locationToReturn;
    }

    /**
     * This method will search for a location in the OpenWeatherMap API and return a Location object if the place and
     * country exists in the API.
     *
     * @param name - Location name (city, state) to search for
     * @param country - Country name to search for, it will be converted to two-letter country code later on
     * @return - Location object
     * @throws JsonProcessingException
     */
    public Location getLocationFromExternalApi(String name, String country) throws JsonProcessingException {
        String countryConvertedToTwoLetter = getCountryCode(country);

        String locationUriRequest = getGeolocationWeatherApiUri + name + "&limit=100" + "&appid=" +getOpenWeatherApiKey;

        Mono<String> jsonResponseMono = webClient.get()
                .uri(locationUriRequest)
                .retrieve()
                .bodyToMono(String.class);

        String jsonResponse = jsonResponseMono.block();



        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Location> locationsFound = objectMapper.readValue(jsonResponse, new TypeReference<List<Location>>() {});



        for (Location location : locationsFound) {

            if (location.getName().equalsIgnoreCase(name) && location.getCountry().equalsIgnoreCase(countryConvertedToTwoLetter)) {
               Location locationToReturn = Location.builder()
                       .name(name)
                       .country(country)
                       .lat(location.getLat())
                       .lon(location.getLon())
                       .build();

               return saveLocation(locationToReturn);
            }
        }

        throw new LocationNotFoundException();
    }
}

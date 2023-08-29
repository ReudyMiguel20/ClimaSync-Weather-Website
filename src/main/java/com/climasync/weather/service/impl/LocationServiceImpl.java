package com.climasync.weather.service.impl;

import com.climasync.weather.exception.LocationNotFoundException;
import com.climasync.weather.model.entity.Location;
import com.climasync.weather.repository.LocationRepository;
import com.climasync.weather.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public Location getLocationByPlaceAndCountry(String place, String country) {
        return locationRepository.findByPlaceAndCountry(place, country)
                .orElseThrow(LocationNotFoundException::new);
    }
}

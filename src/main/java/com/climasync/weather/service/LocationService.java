package com.climasync.weather.service;

import com.climasync.weather.model.entity.Location;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface LocationService {

    Location saveLocation(Location location);

    Location getLocationByNameAndCountry(String name, String country) throws JsonProcessingException;

}

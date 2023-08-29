package com.climasync.weather.service;

import com.climasync.weather.model.entity.Location;

public interface LocationService {

    Location getLocationByPlaceAndCountry(String place, String country);

}

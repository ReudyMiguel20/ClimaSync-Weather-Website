package com.climasync.weather.repository;

import com.climasync.weather.model.entity.CurrentWeather;
import com.climasync.weather.model.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentWeatherRepository extends MongoRepository<CurrentWeather, String> {

    Optional<CurrentWeather> findByLocation(Location location);
}

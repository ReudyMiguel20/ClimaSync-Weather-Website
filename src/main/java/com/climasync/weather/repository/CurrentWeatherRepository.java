package com.climasync.weather.repository;

import com.climasync.weather.model.entity.CurrentWeather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentWeatherRepository extends MongoRepository<CurrentWeather, String> {
}

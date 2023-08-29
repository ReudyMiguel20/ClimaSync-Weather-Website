package com.climasync.weather.repository;


import com.climasync.weather.model.entity.CachedWeather;
import com.climasync.weather.model.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CachedWeatherRepository extends MongoRepository<CachedWeather, String>  {

    CachedWeather findByLocation(Location location);
}

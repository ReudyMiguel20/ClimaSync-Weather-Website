package com.climasync.weather.repository;

import com.climasync.weather.model.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    Optional<Location> findByPlaceAndCountry(String place, String country);

}

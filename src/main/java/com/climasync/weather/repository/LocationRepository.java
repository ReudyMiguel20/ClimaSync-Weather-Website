package com.climasync.weather.repository;

import com.climasync.weather.model.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
}

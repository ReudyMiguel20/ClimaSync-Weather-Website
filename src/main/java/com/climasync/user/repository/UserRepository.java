package com.climasync.user.repository;

import com.climasync.user.model.entity.User;
import com.climasync.weather.model.entity.CurrentWeather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}

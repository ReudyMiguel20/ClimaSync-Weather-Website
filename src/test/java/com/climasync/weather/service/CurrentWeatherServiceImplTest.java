package com.climasync.weather.service;

import com.climasync.weather.service.impl.CurrentWeatherImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@RequiredArgsConstructor
public class CurrentWeatherServiceImplTest {

    private final CurrentWeatherImpl currentWeatherImpl;


}

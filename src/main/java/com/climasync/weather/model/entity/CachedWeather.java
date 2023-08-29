package com.climasync.weather.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class CachedWeather {

    @Id
    public String id;
    public Location location;
    private CurrentWeather data;
    private LocalDateTime timestamp;
}

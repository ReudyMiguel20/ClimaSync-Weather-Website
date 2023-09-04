package com.climasync.weather.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CachedWeather {

    @Id
    private String id;
    @Indexed (unique = true)
    private Location location;
    private CurrentWeather data;
    private LocalDateTime timestamp;

}

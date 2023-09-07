package com.climasync.common.config;

//import com.climasync.common.utils.CustomTempPropertyMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    @Value("${openweatherapi.apiKey}")
    private String openWeatherApiKey;

    @Value("${openweatherapi.geolocationURL}")
    private String geolocationWeatherApiUri;

    @Value("${openweatherapi.currentWeatherDataURL}")
    private String currentWeatherDataUri;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @Bean
//    public ModelMapper tempMapper() {

//    }

    @Bean
    public String getOpenWeatherApiKey() {
        return openWeatherApiKey;
    }

    @Bean
    public String getGeolocationWeatherApiUri() {
        return geolocationWeatherApiUri;
    }

    @Bean
    public String getCurrentWeatherDataUri() {
        return currentWeatherDataUri;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

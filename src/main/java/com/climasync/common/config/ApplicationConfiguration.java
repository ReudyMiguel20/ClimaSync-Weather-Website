package com.climasync.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
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

    @Value("${openweatherapi.geolocationURI}")
    private String geolocationWeatherApiUri;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public String getOpenWeatherApiKey() {
        return openWeatherApiKey;
    }

    @Bean
    public String getGeolocationWeatherApiUri() {
        return geolocationWeatherApiUri;
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

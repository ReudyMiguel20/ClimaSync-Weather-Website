//package com.climasync.common.utils;
//
//import com.climasync.weather.model.entity.CurrentWeather;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.modelmapper.PropertyMap;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//public class CustomTempPropertyMap extends PropertyMap<Map<String, Object>, CurrentWeather> {
//    @Override
//    protected void configure() {
//        map().setFeelsLike(Double.parseDouble(String.valueOf(source.get("feels_like"))));
//    }
//}

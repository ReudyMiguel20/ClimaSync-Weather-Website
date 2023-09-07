//package com.climasync.common.utils;
//
//import com.climasync.weather.model.entity.CurrentWeather;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class WeatherMapper {
//
//    private static final ModelMapper modelMapper = new ModelMapper();
//
//    public WeatherMapper() {
//        modelMapper.addMappings(new CustomTempPropertyMap());
//    }
//
//    public static CurrentWeather mapToCurrentWeather(Map<String, Object> mainValues) {
//        return modelMapper.map(mainValues, CurrentWeather.class);
//    }
//
//
//}

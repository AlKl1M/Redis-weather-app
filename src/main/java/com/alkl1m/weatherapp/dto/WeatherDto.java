package com.alkl1m.weatherapp.dto;

import com.alkl1m.weatherapp.dto.web.WeatherResponse;

import java.util.List;

public record WeatherDto(
        String city,
        List<Weather> weather,
        Main mainInfo,
        int visibility,
        Wind wind,
        Clouds clouds,
        Sys sys
) {
    public static WeatherDto fromWeatherResponse(String cityName, WeatherResponse weatherData) {
        return new WeatherDto(
                cityName,
                weatherData.weather(),
                weatherData.main(),
                weatherData.visibility(),
                weatherData.wind(),
                weatherData.clouds(),
                weatherData.sys()
        );
    }
}
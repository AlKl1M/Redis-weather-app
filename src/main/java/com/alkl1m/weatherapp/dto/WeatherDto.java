package com.alkl1m.weatherapp.dto;

import com.alkl1m.weatherapp.dto.web.WeatherResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @author AlKl1M
 */
public record WeatherDto(
        String city,
        List<Weather> weather,
        Main mainInfo,
        int visibility,
        Wind wind,
        Clouds clouds,
        Sys sys
) implements Serializable {
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

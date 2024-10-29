package com.alkl1m.weatherapp.service;

import com.alkl1m.weatherapp.dto.WeatherDto;

/**
 * @author AlKl1M
 */
public interface WeatherService {

    WeatherDto getWeather(String cityName, String units, String land);

    void cleanWeatherDataCache();

}

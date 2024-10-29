package com.alkl1m.weatherapp.service.impl;

import com.alkl1m.weatherapp.client.GeoClient;
import com.alkl1m.weatherapp.client.WeatherClient;
import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.dto.web.GeoResponse;
import com.alkl1m.weatherapp.dto.web.WeatherResponse;
import com.alkl1m.weatherapp.exception.GeoDataException;
import com.alkl1m.weatherapp.service.GeoService;
import com.alkl1m.weatherapp.service.WeatherService;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${api.openweathermap.key}")
    private String apikey;
    private final WeatherClient weatherClient;
    private final GeoService geoService;

    @Override
    @Cacheable(value = "weatherDataCache", key = "#cityName + '-' + #units + '-' + #lang")
    public WeatherDto getWeather(String cityName, String units, String lang) {
        try {
            List<GeoResponse> getGeoData = geoService.getGeoData(cityName, 1);
            WeatherResponse weatherData = weatherClient.getWeatherData(
                    getGeoData.getFirst().lat(),
                    getGeoData.getFirst().lon(),
                    units,
                    lang,
                    apikey);
            return WeatherDto.fromWeatherResponse(cityName, weatherData);
        } catch (FeignException e) {
            throw new GeoDataException("Error fetching weather data for city: " + cityName, e);
        }
    }

    @CacheEvict(value = "weatherDataCache", allEntries = true)
    public void cleanWeatherDataCache() {
        // @CacheEvict will erase weatherDataCache
    }
}

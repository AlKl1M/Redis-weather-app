package com.alkl1m.weatherapp.service;

import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.dto.web.GeoResponse;

import java.util.List;

public interface WeatherService {

    List<GeoResponse> getGeoData(String cityName, int limit);

    WeatherDto getWeather(String cityName, String units, String land);

}

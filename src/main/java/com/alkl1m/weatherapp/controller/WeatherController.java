package com.alkl1m.weatherapp.controller;

import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.dto.web.GeoResponse;
import com.alkl1m.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public WeatherDto getWeather(@RequestParam String city,
                                 @RequestParam String units,
                                 @RequestParam String lang) {
        return weatherService.getWeather(city, units, lang);
    }

}

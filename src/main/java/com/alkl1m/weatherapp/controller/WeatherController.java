package com.alkl1m.weatherapp.controller;

import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/cache")
    public ResponseEntity<String> clearCache() {
        weatherService.cleanWeatherDataCache();
        return ResponseEntity.ok("Cache cleared.");
    }

}

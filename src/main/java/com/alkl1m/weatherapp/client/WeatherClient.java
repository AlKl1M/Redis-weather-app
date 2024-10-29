package com.alkl1m.weatherapp.client;

import com.alkl1m.weatherapp.dto.web.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherClient", url = "https://api.openweathermap.org/data/2.5")
public interface WeatherClient {

    @GetMapping("/weather")
    WeatherResponse getWeatherData(@RequestParam("lat") double lat,
                                   @RequestParam("lon") double lon,
                                   @RequestParam("units") String units,
                                   @RequestParam("lang") String lang,
                                   @RequestParam("appid") String apiKey);

}

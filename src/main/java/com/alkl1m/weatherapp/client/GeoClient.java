package com.alkl1m.weatherapp.client;

import com.alkl1m.weatherapp.dto.web.GeoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "geoClient", url = "https://api.openweathermap.org/geo/1.0")
public interface GeoClient {

    @GetMapping("/direct")
    List<GeoResponse> getGeoData(@RequestParam("q") String targetName,
                                 @RequestParam("limit") int limit,
                                 @RequestParam("appid") String apiKey);
}

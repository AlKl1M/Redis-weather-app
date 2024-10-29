package com.alkl1m.weatherapp.service.impl;

import com.alkl1m.weatherapp.client.GeoClient;
import com.alkl1m.weatherapp.dto.web.GeoResponse;
import com.alkl1m.weatherapp.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

    @Value("${api.openweathermap.key}")
    private String apikey;
    private final GeoClient geoClient;

    @Override
    public List<GeoResponse> getGeoData(String cityName, int limit) {
        return geoClient.getGeoData(cityName, limit, apikey);
    }
}

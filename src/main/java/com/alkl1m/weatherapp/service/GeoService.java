package com.alkl1m.weatherapp.service;

import com.alkl1m.weatherapp.dto.web.GeoResponse;

import java.util.List;

public interface GeoService {

    List<GeoResponse> getGeoData(String cityName, int limit);

}

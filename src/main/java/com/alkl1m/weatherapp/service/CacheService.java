package com.alkl1m.weatherapp.service;

import com.alkl1m.weatherapp.dto.WeatherDto;

import java.time.Duration;

public interface CacheService {

    WeatherDto getFromCache(String cacheName, String key);

    void putInCache(String cacheName, String key, WeatherDto value, Duration ttl);

    void evictFromCache(String cacheName, String key);

    void evictCacheCategory(String cacheName);

}

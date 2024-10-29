package com.alkl1m.weatherapp.service.impl;

import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.*;

@Testcontainers
@SpringBootTest
@ActiveProfiles("dev")
class WeatherServiceImplTest {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CacheManager cacheManager;

    static {
        GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:latest"))
                .withExposedPorts(6379);
        redis.start();
        System.setProperty("spring.data.redis.host", redis.getHost());
        System.setProperty("spring.data.redis.port", redis.getMappedPort(6379).toString());
    }

    @Test
    void testGetWeather_withValidPayload_returnsValidData() {
        WeatherDto weather = weatherService.getWeather("Kaliningrad", "standart", "ru");
        assertEquals("Kaliningrad", weather.city());
        assertNotNull(weather.weather());
        assertNotNull(weather.mainInfo());
        assertNotNull(weather.wind());
        assertNotNull(weather.clouds());
        assertNotNull(weather.sys());
    }

    @Test
    void testGetWeather_withValidPayload_SavesToCache() {
        WeatherDto weather = weatherService.getWeather("Moscow", "standart", "ru");

        var cache = cacheManager.getCache("weatherDataCache");
        var cachedValue = cache.get("Moscow" + "-" + "standart" + "-" + "ru", WeatherDto.class);

        assertEquals(weather.city(), cachedValue.city());
        assertEquals(weather.weather(), cachedValue.weather());
        assertEquals(weather.mainInfo(), cachedValue.mainInfo());
        assertEquals(weather.wind(), cachedValue.wind());
        assertEquals(weather.clouds(), cachedValue.clouds());
        assertEquals(weather.sys(), cachedValue.sys());
    }

    @Test
    void testCleanWeatherData_withValidPayload_WipesCache() {
        weatherService.getWeather("Moscow", "standart", "ru");
        weatherService.getWeather("Kaliningrad", "standart", "ru");
        weatherService.getWeather("Krakow", "standart", "ru");

        weatherService.cleanWeatherDataCache();

        Cache cache = cacheManager.getCache("weatherDataCache");

        assertNull(cache.get("Moscow" + "-" + "standart" + "-" + "ru", WeatherDto.class));
        assertNull(cache.get("Kaliningrad" + "-" + "standart" + "-" + "ru", WeatherDto.class));
        assertNull(cache.get("Krakow" + "-" + "standart" + "-" + "ru", WeatherDto.class));

    }

}
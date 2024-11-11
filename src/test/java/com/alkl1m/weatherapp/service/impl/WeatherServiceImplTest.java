package com.alkl1m.weatherapp.service.impl;

import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.service.CacheService;
import com.alkl1m.weatherapp.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private CacheService cacheService;

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

        WeatherDto cachedValue = cacheService.getFromCache("weatherDataCache", "Moscow-standart-ru");

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

        cacheService.evictCacheCategory("weatherDataCache");

        assertNull(cacheService.getFromCache("weatherDataCache", "Moscow-standart-ru"));
        assertNull(cacheService.getFromCache("weatherDataCache", "Kaliningrad-standart-ru"));
        assertNull(cacheService.getFromCache("weatherDataCache", "Krakow-standart-ru"));
    }

    @Test
    void testEvictSpecificWeatherData() {
        weatherService.getWeather("Moscow", "standard", "ru");
        weatherService.getWeather("Kaliningrad", "standard", "ru");
        WeatherDto moscowWeatherBeforeEvict = cacheService.getFromCache("weatherDataCache", "Moscow-standard-ru");
        WeatherDto kaliningradWeatherBeforeEvict = cacheService.getFromCache("weatherDataCache", "Kaliningrad-standard-ru");

        assertNotNull(moscowWeatherBeforeEvict);
        assertNotNull(kaliningradWeatherBeforeEvict);

        cacheService.evictCacheCategory("weatherDataCache");

        WeatherDto moscowWeatherAfterEvict = cacheService.getFromCache("weatherDataCache", "Moscow-standard-ru");
        WeatherDto kaliningradWeatherAfterEvict = cacheService.getFromCache("weatherDataCache", "Kaliningrad-standard-ru");

        assertNull(moscowWeatherAfterEvict);
        assertNull(kaliningradWeatherAfterEvict);
    }

}

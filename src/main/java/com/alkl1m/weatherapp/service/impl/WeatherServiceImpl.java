package com.alkl1m.weatherapp.service.impl;

import com.alkl1m.weatherapp.client.WeatherClient;
import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.dto.web.GeoResponse;
import com.alkl1m.weatherapp.dto.web.WeatherResponse;
import com.alkl1m.weatherapp.exception.GeoDataException;
import com.alkl1m.weatherapp.service.GeoService;
import com.alkl1m.weatherapp.service.WeatherService;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса для работы с погодными данными.
 *
 * @author AlKl1M
 */
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${api.openweathermap.key}")
    private String apikey;

    private final WeatherClient weatherClient;
    private final GeoService geoService;

    /**
     * Получает погодные данные для указанного города.
     * <p>
     * Метод использует кеширование, чтобы избежать повторных запросов
     * к API для одних и тех же параметров.
     *
     * @param cityName название города, для которого необходимо получить погодные данные
     * @param units    единицы измерения для погодных данных (например, метрическая или имперская)
     * @param lang     язык для описания погодных данных
     * @return объект WeatherDto, содержащий информацию о погоде
     * @throws GeoDataException если возникает ошибка при получении данных о погоде
     */
    @Override
    @Cacheable(value = "weatherDataCache", key = "#cityName + '-' + #units + '-' + #lang")
    public WeatherDto getWeather(String cityName, String units, String lang) {
        try {
            List<GeoResponse> getGeoData = geoService.getGeoData(cityName, 1);
            WeatherResponse weatherData = weatherClient.getWeatherData(
                    getGeoData.get(0).lat(),
                    getGeoData.get(0).lon(),
                    units,
                    lang,
                    apikey);
            return WeatherDto.fromWeatherResponse(cityName, weatherData);
        } catch (FeignException e) {
            throw new GeoDataException("Error fetching weather data for city: " + cityName, e);
        }
    }

    /**
     * Очищает кэш данных о погоде.
     * <p>
     * Этот метод удаляет все записи в кэше для weatherDataCache.
     */
    @CacheEvict(value = "weatherDataCache", allEntries = true)
    public void cleanWeatherDataCache() {
        // @CacheEvict будет очищать weatherDataCache
    }

}

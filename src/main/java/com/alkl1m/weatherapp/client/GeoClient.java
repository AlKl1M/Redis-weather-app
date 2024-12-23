package com.alkl1m.weatherapp.client;

import com.alkl1m.weatherapp.dto.web.GeoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Клиент для получения географических данных из OpenWeatherMap API.
 *
 * @author AlKl1M
 */
@FeignClient(name = "geoClient", url = "https://api.openweathermap.org/geo/1.0")
public interface GeoClient {

    /**
     * Получает географические данные по заданному названию места.
     *
     * @param targetName название места, для которого требуется получить данные
     * @param limit      максимальное количество возвращаемых результатов
     * @param apiKey     ключ API для авторизации запросов
     * @return список объектов GeoResponse, содержащих географические данные
     */
    @GetMapping("/direct")
    List<GeoResponse> getGeoData(@RequestParam("q") String targetName,
                                 @RequestParam("limit") int limit,
                                 @RequestParam("appid") String apiKey);
}

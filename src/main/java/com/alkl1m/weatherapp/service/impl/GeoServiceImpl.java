package com.alkl1m.weatherapp.service.impl;

import com.alkl1m.weatherapp.client.GeoClient;
import com.alkl1m.weatherapp.dto.web.GeoResponse;
import com.alkl1m.weatherapp.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса для работы с геоданными.
 *
 * @author AlKl1M
 */
@Service
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

    @Value("${api.openweathermap.key}")
    private String apikey;

    private final GeoClient geoClient;

    /**
     * Получает географические данные для указанного названия города.
     *
     * @param cityName название города, для которого необходимо получить геоданные
     * @param limit    максимальное количество возвращаемых результатов
     * @return список объектов GeoResponse, содержащих географические данные
     */
    @Override
    public List<GeoResponse> getGeoData(String cityName, int limit) {
        return geoClient.getGeoData(cityName, limit, apikey);
    }

}

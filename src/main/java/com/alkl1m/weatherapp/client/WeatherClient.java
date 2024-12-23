package com.alkl1m.weatherapp.client;

import com.alkl1m.weatherapp.dto.web.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Клиент для получения погодных данных из OpenWeatherMap API.
 *
 * @author AlKl1M
 */
@FeignClient(name = "weatherClient", url = "https://api.openweathermap.org/data/2.5")
public interface WeatherClient {

    /**
     * Получает данные о погоде по заданным координатам.
     *
     * @param lat    широта местоположения
     * @param lon    долгота местоположения
     * @param units  единицы измерения для погодных данных (например, метрическая или имперская)
     * @param lang   язык для описания погодных данных
     * @param apiKey ключ API для авторизации запросов
     * @return объект WeatherResponse, содержащий данные о погоде
     */
    @GetMapping("/weather")
    WeatherResponse getWeatherData(@RequestParam("lat") double lat,
                                   @RequestParam("lon") double lon,
                                   @RequestParam("units") String units,
                                   @RequestParam("lang") String lang,
                                   @RequestParam("appid") String apiKey);

}

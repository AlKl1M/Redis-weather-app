package com.alkl1m.weatherapp.controller;

import com.alkl1m.weatherapp.dto.WeatherDto;
import com.alkl1m.weatherapp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с погодными данными.
 *
 * @author AlKl1M
 */
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Tag(name = "API контроллера погоды", description = "Контроллер для работы с данными о погоде.")
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * Получает данные о погоде для указанного города.
     *
     * @param city  название города, для которого необходимо получить данные о погоде
     * @param units единицы измерения для погодных данных (например, метрическая или имперская)
     * @param lang  язык для описания погодных данных
     * @return объект WeatherDto, содержащий информацию о погоде
     */
    @Operation(summary = "Получение данных о погоде для указанного города",
            description = "получает данные о конкретном городе в определенных единицах измерения и на определенном языке.")
    @GetMapping
    public WeatherDto getWeather(@RequestParam String city,
                                 @RequestParam String units,
                                 @RequestParam String lang) {
        return weatherService.getWeather(city, units, lang);
    }

    /**
     * Очищает кэш данных о погоде.
     *
     * @return ответ с сообщением об успешной очистке кэша
     */
    @Operation(summary = "Полная очистка кэша погоды",
            description = "Очищает весь кэш данных о погоде.")
    @DeleteMapping("/cache")
    public ResponseEntity<String> clearCache() {
        weatherService.cleanWeatherDataCache();
        return ResponseEntity.ok("Cache cleared.");
    }

}

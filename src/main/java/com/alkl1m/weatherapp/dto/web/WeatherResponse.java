package com.alkl1m.weatherapp.dto.web;

import com.alkl1m.weatherapp.dto.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author AlKl1M
 */
public record WeatherResponse(
        @JsonProperty("coord")
        @Schema(description = "Координаты")
        Coord coord,
        @JsonProperty("weather")
        @Schema(description = "Погода")
        List<Weather> weather,
        @JsonProperty("base")
        @Schema(description = "Базовые данные")
        String base,
        @JsonProperty("main")
        @Schema(description = "Основные данные")
        Main main,
        @JsonProperty("visibility")
        @Schema(description = "Видимость")
        int visibility,
        @JsonProperty("wind")
        @Schema(description = "Ветен")
        Wind wind,
        @JsonProperty("clouds")
        @Schema(description = "Облачность")
        Clouds clouds,
        @JsonProperty("dt")
        @Schema(description = "Текущее время")
        long dt,
        @JsonProperty("sys")
        Sys sys,
        @JsonProperty("timezone")
        @Schema(description = "Часовой пояс")
        int timezone,
        @JsonProperty("id")
        int id,
        @JsonProperty("name")
        String name,
        @JsonProperty("cod")
        int cod
) {
    public record Coord(
            @JsonProperty("lon")
            double lon,
            @JsonProperty("lat")
            double lat
    ) {
    }

}

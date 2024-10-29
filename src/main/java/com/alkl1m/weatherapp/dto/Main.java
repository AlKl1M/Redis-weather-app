package com.alkl1m.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * @author AlKl1M
 */
public record Main(
        @JsonProperty("temp")
        @Schema(description = "Температура")
        double temp,
        @JsonProperty("feels_like")
        @Schema(description = "Как ощущается")
        double feelsLike,
        @JsonProperty("temp_min")
        @Schema(description = "Минимальная температура")
        double tempMin,
        @JsonProperty("temp_max")
        @Schema(description = "Максимальная температура")
        double tempMax,
        @JsonProperty("pressure")
        @Schema(description = "Давление")
        int pressure,
        @JsonProperty("humidity")
        @Schema(description = "Влажность")
        int humidity,
        @JsonProperty("sea_level")
        @Schema(description = "Уровень моря")
        int seaLevel,
        @JsonProperty("grnd_level")
        @Schema(description = "Уровень земли")
        int grndLevel
) implements Serializable {}

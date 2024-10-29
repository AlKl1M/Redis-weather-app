package com.alkl1m.weatherapp.dto.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * @author AlKl1M
 */
public record GeoResponse(
        @JsonProperty("name")
        @Schema(description = "Название")
        String name,
        @JsonProperty("localNames")
        @Schema(description = "Локальное название")
        LocalNames localNames,
        @JsonProperty("lat")
        @Schema(description = "Широта")
        double lat,
        @JsonProperty("lon")
        @Schema(description = "Долгота")
        double lon,
        @JsonProperty("country")
        @Schema(description = "Страна")
        String country,
        @JsonProperty("state")
        @Schema(description = "Штат (для США)")
        String state
) implements Serializable {
    public record LocalNames(
            @JsonProperty("en") String englishName
    ) {}

}

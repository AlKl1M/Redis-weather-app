package com.alkl1m.weatherapp.dto.web;

import com.alkl1m.weatherapp.dto.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WeatherResponse(
        @JsonProperty("coord")
        Coord coord,
        @JsonProperty("weather")
        List<Weather> weather,
        @JsonProperty("base")
        String base,
        @JsonProperty("main")
        Main main,
        @JsonProperty("visibility")
        int visibility,
        @JsonProperty("wind")
        Wind wind,
        @JsonProperty("clouds")
        Clouds clouds,
        @JsonProperty("dt")
        long dt,
        @JsonProperty("sys")
        Sys sys,
        @JsonProperty("timezone")
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

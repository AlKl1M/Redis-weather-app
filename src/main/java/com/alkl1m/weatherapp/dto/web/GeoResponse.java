package com.alkl1m.weatherapp.dto.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record GeoResponse(
        @JsonProperty("name")
        String name,
        @JsonProperty("localNames")
        LocalNames localNames,
        @JsonProperty("lat")
        double lat,
        @JsonProperty("lon")
        double lon,
        @JsonProperty("country")
        String country,
        @JsonProperty("state")
        String state
) implements Serializable {
    public record LocalNames(
            @JsonProperty("en") String englishName
    ) {}
}

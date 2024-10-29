package com.alkl1m.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Clouds(
        @JsonProperty("all")
        int all
) {
}
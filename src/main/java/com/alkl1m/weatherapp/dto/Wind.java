package com.alkl1m.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record Wind(
        @JsonProperty("speed")
        double speed,
        @JsonProperty("deg")
        int deg,
        @JsonProperty("gust")
        double gust
) implements Serializable {}
package com.alkl1m.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record Clouds(
        @JsonProperty("all")
        int all
) implements Serializable {
}
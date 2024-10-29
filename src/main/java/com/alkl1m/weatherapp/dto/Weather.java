package com.alkl1m.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record Weather(
        @JsonProperty("id")
        int id,
        @JsonProperty("main")
        String main,
        @JsonProperty("description")
        String description,
        @JsonProperty("icon")
        String icon
) implements Serializable {}

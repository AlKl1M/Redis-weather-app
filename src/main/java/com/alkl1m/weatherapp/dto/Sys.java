package com.alkl1m.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author AlKl1M
 */
public record Sys(
        @JsonProperty("type")
        int type,
        @JsonProperty("id")
        int id,
        @JsonProperty("country")
        String country,
        @JsonProperty("sunrise")
        long sunrise,
        @JsonProperty("sunset")
        long sunset
) implements Serializable {}

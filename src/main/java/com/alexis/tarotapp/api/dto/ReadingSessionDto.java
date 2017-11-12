package com.alexis.tarotapp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadingSessionDto {
    private final long id;
    private final String description;

    public ReadingSessionDto(@JsonProperty("id") long id,
                             @JsonProperty("description") String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}

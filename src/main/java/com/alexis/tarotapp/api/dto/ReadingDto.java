package com.alexis.tarotapp.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadingDto {
    private final long id;
    private final MeaningDto meaning;
    private final SpreadComponentDto spreadComponent;

    @JsonCreator
    public ReadingDto(@JsonProperty("id") long id,
                      @JsonProperty("meaning") MeaningDto meaning,
                      @JsonProperty("spreadComponent") SpreadComponentDto spreadComponent) {
        this.id = id;
        this.meaning = meaning;
        this.spreadComponent = spreadComponent;
    }

    public long getId() {
        return id;
    }

    public MeaningDto getMeaning() {
        return meaning;
    }

    public SpreadComponentDto getSpreadComponent() {
        return spreadComponent;
    }
}

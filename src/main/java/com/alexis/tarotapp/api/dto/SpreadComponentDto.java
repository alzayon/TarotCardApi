package com.alexis.tarotapp.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpreadComponentDto {
    private final long id;
    private final int position;
    private final SpreadDto spread;

    @JsonCreator
    public SpreadComponentDto(@JsonProperty("id") long id,
                              @JsonProperty("position") int position,
                              @JsonProperty("spreadId") SpreadDto spread) {
        this.id = id;
        this.position = position;
        this.spread = spread;
    }

    public long getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public SpreadDto getSpread() {
        return spread;
    }
}

package com.alexis.tarotapp.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

/**
 * Created by alzayon on 8/20/2017.
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDto {

    private final int id;

    private final String name;

    private final int type;

    @JsonCreator
    public CardDto(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("type") int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}

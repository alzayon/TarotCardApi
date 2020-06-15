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
public class CategoryDto {
    private int id;

    private String name;

    @JsonCreator
    public CategoryDto(@JsonProperty("id") int id,
                       @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

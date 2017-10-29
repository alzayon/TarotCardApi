package com.alexis.tarotapp.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MeaningDto {
    private final int id;
    private final CardDto card;
    private final CategoryDto category;
    private final String meaningText;


    @JsonCreator
    public MeaningDto(@JsonProperty("id") int id,
                      @JsonProperty("card") CardDto card,
                      @JsonProperty("category") CategoryDto category,
                      @JsonProperty("meaning_text") String meaningText) {
        this.id = id;
        this.card = card;
        this.category = category;
        this.meaningText = meaningText;
    }

    public int getId() {
        return id;
    }

    public CardDto getCard() {
        return card;
    }

    public CategoryDto getCategory() {
        return category;
    }

    @JsonProperty("meaning_text")
    public String getMeaningText() {
        return meaningText;
    }
}

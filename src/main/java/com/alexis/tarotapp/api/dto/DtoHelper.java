package com.alexis.tarotapp.api.dto;

import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.entities.Category;

/**
 * Created by alzayon on 8/20/2017.
 */
public final class DtoHelper {
    private DtoHelper() {

    }

    public static CardDto toDto(Card card) {
        return new CardDto(card.getId(), card.getName(), card.getType());
    }

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}

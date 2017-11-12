package com.alexis.tarotapp.api.dto.helper;

import com.alexis.tarotapp.api.dto.*;
import com.alexis.tarotapp.api.entities.*;

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

    public static MeaningDto toDto(Meaning meaning) {
        return new MeaningDto(meaning.getId(),
                toDto(meaning.getCard()),
                toDto(meaning.getCategory()),
                meaning.getMeaningText());

    }

    public static SpreadDto toDto(Spread spread) {
        return new SpreadDto(spread.getId(),
                spread.getName());
    }

    public static SpreadComponentDto toDto(SpreadComponent spreadComponent) {
        return new SpreadComponentDto(spreadComponent.getId(),
                spreadComponent.getPosition(),
                toDto(spreadComponent.getSpread()));
    }

    public static ReadingSessionDto toDto(ReadingSession readingSession) {
        return new ReadingSessionDto(readingSession.getId(),
                readingSession.getDescription());
    }

    public static ReadingDto toDto(Reading reading) {
        return new ReadingDto(reading.getId(),
                toDto(reading.getMeaning()),
                toDto(reading.getSpreadComponent())
        );
    }
}

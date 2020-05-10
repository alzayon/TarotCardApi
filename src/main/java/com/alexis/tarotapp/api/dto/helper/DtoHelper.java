package com.alexis.tarotapp.api.dto.helper;

import com.alexis.tarotapp.api.dto.*;
import com.alexis.tarotapp.api.dto.listing.*;
import com.alexis.tarotapp.api.entities.*;
import com.alexis.tarotapp.api.repository.listing.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public static CardListingResultDto toDto(CardListingResult cardListingResult) {
        final List<CardDto> cards = cardListingResult.getListing().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        return new CardListingResultDto(cardListingResult.getCount(), cards);
    }

    public static CategoryListingResultDto toDto(CategoryListingResult categoryListingResult) {
        final List<CategoryDto> categories = categoryListingResult.getListing().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        return new CategoryListingResultDto(categoryListingResult.getCount(), categories);
    }

    public static MeaningListingResultDto toDto(MeaningListingResult meaningListingResult) {
        final List<MeaningDto> meanings = meaningListingResult.getListing().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        return new MeaningListingResultDto(meaningListingResult.getCount(), meanings);
    }

    public static ReadingListingResultDto toDto(ReadingListingResult readingListingResult) {
        final List<ReadingDto> readings = readingListingResult.getListing().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        return new ReadingListingResultDto(readingListingResult.getCount(), readings);
    }

    public static ReadingSessionListingResultDto toDto(ReadingSessionListingResult readingSessionListingResult) {
        final List<ReadingSessionDto> readingSessions = readingSessionListingResult.getListing().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        return new ReadingSessionListingResultDto(readingSessionListingResult.getCount(), readingSessions);
    }

    public static SpreadListingResultDto toDto(SpreadListingResult spreadListingResult) {
        final List<SpreadDto> spreadSessions = spreadListingResult.getListing().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        return new SpreadListingResultDto(spreadListingResult.getCount(), spreadSessions);
    }
}

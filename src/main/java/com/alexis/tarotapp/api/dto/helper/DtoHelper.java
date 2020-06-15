package com.alexis.tarotapp.api.dto.helper;

import com.alexis.tarotapp.api.dto.*;
import com.alexis.tarotapp.api.dto.listing.*;
import com.alexis.tarotapp.api.entities.*;
import com.alexis.tarotapp.api.mappers.*;
import com.alexis.tarotapp.api.repository.listing.*;

/**
 * Created by alzayon on 8/20/2017.
 */
public final class DtoHelper {

    private static ICardMapper cardMapper = new ICardMapperImpl();
    private static ICategoryMapper categoryMapper = new ICategoryMapperImpl();
    private static IMeaningMapper meaningMapper = new IMeaningMapperImpl();
    private static IReadingMapper readingMapper = new IReadingMapperImpl();
    private static IReadingSessionMapper readingSessionMapper = new IReadingSessionMapperImpl();
    private static ISpreadComponentMapper spreadComponentMapper = new ISpreadComponentMapperImpl();
    private static ISpreadMapper spreadMapper = new ISpreadMapperImpl();

    private DtoHelper() {
    }

    public static CardDto toDto(Card card) {
        return cardMapper.entityToDto(card);
    }

    public static CategoryDto toDto(Category category) {
        return categoryMapper.entityToDto(category);
    }

    public static MeaningDto toDto(Meaning meaning) {
        return meaningMapper.entityToDto(meaning);
    }

    public static SpreadDto toDto(Spread spread) {
        return spreadMapper.entityToDto(spread);
    }

    public static SpreadComponentDto toDto(SpreadComponent spreadComponent) {
        return spreadComponentMapper.entityToDto(spreadComponent);
    }

    public static ReadingSessionDto toDto(ReadingSession readingSession) {
        return readingSessionMapper.entityToDto(readingSession);
    }

    public static ReadingDto toDto(Reading reading) {
        return readingMapper.entityToDto(reading);
    }

    public static CardListingResultDto toDto(CardListingResult cardListingResult) {
        return cardMapper.entityToDto(cardListingResult);
    }

    public static CategoryListingResultDto toDto(CategoryListingResult categoryListingResult) {
        return categoryMapper.entityToDto(categoryListingResult);
    }

    public static MeaningListingResultDto toDto(MeaningListingResult meaningListingResult) {
        return meaningMapper.entityToDto(meaningListingResult);
    }

    public static ReadingListingResultDto toDto(ReadingListingResult readingListingResult) {
        return readingMapper.entityToDto(readingListingResult);
    }

    public static ReadingSessionListingResultDto toDto(ReadingSessionListingResult readingSessionListingResult) {
        return readingSessionMapper.entityToDto(readingSessionListingResult);
    }

    public static SpreadComponentListingResultDto toDto(SpreadComponentListingResult spreadComponentListingResult) {
        return spreadComponentMapper.entityToDto(spreadComponentListingResult);
    }

    public static SpreadListingResultDto toDto(SpreadListingResult spreadListingResult) {
        return spreadMapper.entityToDto(spreadListingResult);
    }
}

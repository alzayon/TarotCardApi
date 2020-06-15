package com.alexis.tarotapp.api.entities.helper;

import com.alexis.tarotapp.api.dto.*;
import com.alexis.tarotapp.api.entities.*;
import com.alexis.tarotapp.api.mappers.*;
import org.hibernate.Session;

public class EntityHelper {

    private static ICardMapper cardMapper = new ICardMapperImpl();
    private static ICategoryMapper categoryMapper = new ICategoryMapperImpl();
    private static IMeaningMapper meaningMapper = new IMeaningMapperImpl();
    private static IReadingMapper readingMapper = new IReadingMapperImpl();
    private static IReadingSessionMapper readingSessionMapper = new IReadingSessionMapperImpl();
    private static ISpreadComponentMapper spreadComponentMapper = new ISpreadComponentMapperImpl();
    private static ISpreadMapper spreadMapper = new ISpreadMapperImpl();


    public static Card toEntity(Session session, CardDto cardDto) {
        return cardMapper.dtoToEntity(session, cardDto);
    }

    public static Category toEntity(Session session, CategoryDto categoryDto) {
        return categoryMapper.dtoToEntity(session, categoryDto);
    }

    public static Meaning toEntity(Session session, MeaningDto meaningDto) {
        final Meaning meaning = meaningMapper.dtoToEntity(session, meaningDto);
        return meaning;
    }

    public static Spread toEntity(Session session, SpreadDto spreadDto) {
        return spreadMapper.dtoToEntity(session, spreadDto);
    }

    public static SpreadComponent toEntity(Session session, SpreadComponentDto spreadComponentDto) {
        return spreadComponentMapper.dtoToEntity(session, spreadComponentDto);
    }

    public static ReadingSession toEntity(Session session, ReadingSessionDto readingSessionDto) {
        return readingSessionMapper.dtoToEntity(session, readingSessionDto);
    }

    public static Reading toEntity(Session session, ReadingDto readingDto) {
        final Meaning meaning = toEntity(session, readingDto.getMeaning());
        final SpreadComponent spreadComponent = (SpreadComponent) session.get(SpreadComponent.class,
                                                                      readingDto.getSpreadComponent().getId());
        final Reading reading = new Reading(readingDto.getId(),
                                            meaning,
                                            spreadComponent);
        return reading;
    }



}

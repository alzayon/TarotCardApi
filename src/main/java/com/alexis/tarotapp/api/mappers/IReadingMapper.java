package com.alexis.tarotapp.api.mappers;

import com.alexis.tarotapp.api.dto.ReadingDto;
import com.alexis.tarotapp.api.dto.listing.ReadingListingResultDto;
import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.entities.Reading;
import com.alexis.tarotapp.api.entities.SpreadComponent;
import com.alexis.tarotapp.api.repository.listing.ReadingListingResult;
import org.hibernate.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class IReadingMapper {

    @Mapping(target = "meaning", expression = "java(fetchMeaning(session, readingDto.getMeaning().getId()))")
    @Mapping(target = "spreadComponent", expression = "java(fetchSpreadComponent(session, readingDto" +
            ".getSpreadComponent().getId()))")
    public abstract Reading dtoToEntity(Session session, ReadingDto readingDto);

    public abstract ReadingDto entityToDto(Reading reading);

    public abstract ReadingListingResultDto entityToDto(ReadingListingResult readingListingResult);

    Meaning fetchMeaning(Session session, long meaningId) {
        return (Meaning) session.get(Meaning.class, meaningId);
    }

    SpreadComponent fetchSpreadComponent(Session session, long spreadComponentId) {
        return (SpreadComponent) session.get(Category.class, spreadComponentId);
    }
}

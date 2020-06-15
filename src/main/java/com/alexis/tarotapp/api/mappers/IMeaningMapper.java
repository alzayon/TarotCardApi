package com.alexis.tarotapp.api.mappers;

import com.alexis.tarotapp.api.dto.MeaningDto;
import com.alexis.tarotapp.api.dto.listing.MeaningListingResultDto;
import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.repository.listing.MeaningListingResult;
import org.hibernate.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class IMeaningMapper {

    @Mapping(target = "card", expression = "java(fetchCard(session, meaningDto.getCard().getId()))")
    @Mapping(target = "category", expression = "java(fetchCategory(session, meaningDto.getCategory().getId()))")
    public abstract Meaning dtoToEntity(Session session, MeaningDto meaningDto);

    public abstract MeaningDto entityToDto(Meaning meaning);

    Card fetchCard(Session session, int cardId) {
        return (Card) session.get(Card.class, cardId);
    }

    Category fetchCategory(Session session, int categoryId) {
        return (Category) session.get(Category.class, categoryId);
    }

    public abstract MeaningListingResultDto entityToDto(MeaningListingResult meaningListingResult);
}

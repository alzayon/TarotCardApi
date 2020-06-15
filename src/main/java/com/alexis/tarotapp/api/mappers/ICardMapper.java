package com.alexis.tarotapp.api.mappers;

import com.alexis.tarotapp.api.dto.CardDto;
import com.alexis.tarotapp.api.dto.listing.CardListingResultDto;
import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.repository.listing.CardListingResult;
import org.hibernate.Session;
import org.mapstruct.Mapper;

// https://blog.jdriven.com/2019/07/mapstruct-and-lombok/
@Mapper
public interface ICardMapper {
    Card dtoToEntity(Session session, CardDto cardDto);
    CardDto entityToDto(Card card);

    CardListingResultDto entityToDto(CardListingResult cardListingResult);
}

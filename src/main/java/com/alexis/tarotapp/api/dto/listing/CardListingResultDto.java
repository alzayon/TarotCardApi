package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.CardDto;

import java.util.List;

public class CardListingResultDto extends ListingResultDto<CardDto> {

    public CardListingResultDto(Long count, List<CardDto> listing) {
        super(count, listing);
    }

    public CardListingResultDto(Throwable error) {
        super(error);
    }
}

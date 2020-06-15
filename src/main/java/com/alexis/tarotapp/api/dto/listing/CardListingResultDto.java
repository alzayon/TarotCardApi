package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.CardDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CardListingResultDto extends ListingResultDto<CardDto> {
    public CardListingResultDto(Long count, List<CardDto> listing) {
        super(count, listing);
    }
}

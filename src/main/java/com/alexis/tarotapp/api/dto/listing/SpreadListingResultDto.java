package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.SpreadDto;

import java.util.List;

public class SpreadListingResultDto extends ListingResultDto<SpreadDto> {
    public SpreadListingResultDto(Long count, List<SpreadDto> listing) {
        super(count, listing);
    }

    public SpreadListingResultDto(Throwable error) {
        super(error);
    }
}

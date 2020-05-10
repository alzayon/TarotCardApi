package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.MeaningDto;

import java.util.List;

public class MeaningListingResultDto extends ListingResultDto<MeaningDto> {
    public MeaningListingResultDto(Long count, List<MeaningDto> listing) {
        super(count, listing);
    }

    public MeaningListingResultDto(Throwable error) {
        super(error);
    }
}

package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.ReadingDto;

import java.util.List;

public class ReadingListingResultDto extends ListingResultDto<ReadingDto> {
    public ReadingListingResultDto(Long count, List<ReadingDto> listing) {
        super(count, listing);
    }

    public ReadingListingResultDto(Throwable error) {
        super(error);
    }
}

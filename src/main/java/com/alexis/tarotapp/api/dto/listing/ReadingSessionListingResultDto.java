package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.ReadingSessionDto;

import java.util.List;

public class ReadingSessionListingResultDto extends ListingResultDto<ReadingSessionDto> {
    public ReadingSessionListingResultDto(Long count, List<ReadingSessionDto> listing) {
        super(count, listing);
    }

    public ReadingSessionListingResultDto(Throwable error) {
        super(error);
    }
}

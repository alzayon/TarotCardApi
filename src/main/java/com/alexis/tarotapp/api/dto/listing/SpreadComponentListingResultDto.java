package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.SpreadComponentDto;

import java.util.List;

public class SpreadComponentListingResultDto extends ListingResultDto<SpreadComponentDto> {
    public SpreadComponentListingResultDto(Long count, List<SpreadComponentDto> listing) {
        super(count, listing);
    }

    public SpreadComponentListingResultDto(Throwable error) {
        super(error);
    }
}

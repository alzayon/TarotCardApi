package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.ReadingSessionDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ReadingSessionListingResultDto extends ListingResultDto<ReadingSessionDto> {
    public ReadingSessionListingResultDto(Long count, List<ReadingSessionDto> listing) {
        super(count, listing);
    }
}

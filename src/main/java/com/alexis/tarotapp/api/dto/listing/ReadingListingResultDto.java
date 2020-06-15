package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.ReadingDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ReadingListingResultDto extends ListingResultDto<ReadingDto> {
    public ReadingListingResultDto(Long count, List<ReadingDto> listing) {
        super(count, listing);
    }
}

package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.MeaningDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MeaningListingResultDto extends ListingResultDto<MeaningDto> {
    public MeaningListingResultDto(Long count, List<MeaningDto> listing) {
        super(count, listing);
    }
}

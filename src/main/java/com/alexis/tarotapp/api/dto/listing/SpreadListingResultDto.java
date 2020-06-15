package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.SpreadDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SpreadListingResultDto extends ListingResultDto<SpreadDto> {
    public SpreadListingResultDto(Long count, List<SpreadDto> listing) {
        super(count, listing);
    }
}

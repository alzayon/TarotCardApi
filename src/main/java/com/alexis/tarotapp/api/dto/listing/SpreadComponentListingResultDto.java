package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.SpreadComponentDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SpreadComponentListingResultDto extends ListingResultDto<SpreadComponentDto> {
    public SpreadComponentListingResultDto(Long count,
                                           List<SpreadComponentDto> listing) {
        super(count, listing);
    }
}

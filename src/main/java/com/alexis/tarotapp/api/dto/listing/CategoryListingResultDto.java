package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.CategoryDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CategoryListingResultDto extends ListingResultDto<CategoryDto> {
    public CategoryListingResultDto(Long count, List<CategoryDto> listing) {
        super(count, listing);
    }
}

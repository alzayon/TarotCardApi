package com.alexis.tarotapp.api.dto.listing;

import com.alexis.tarotapp.api.dto.CategoryDto;

import java.util.List;

public class CategoryListingResultDto extends ListingResultDto<CategoryDto> {
    public CategoryListingResultDto(Long count, List<CategoryDto> listing) {
        super(count, listing);
    }

    public CategoryListingResultDto(Throwable error) {
        super(error);
    }
}

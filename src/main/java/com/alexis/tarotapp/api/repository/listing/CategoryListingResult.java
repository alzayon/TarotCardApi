package com.alexis.tarotapp.api.repository.listing;

import com.alexis.tarotapp.api.entities.Category;

import java.util.List;

public class CategoryListingResult extends ListingResult<Category> {
    public CategoryListingResult(Long count, List<Category> listing) {
        super(count, listing);
    }
}

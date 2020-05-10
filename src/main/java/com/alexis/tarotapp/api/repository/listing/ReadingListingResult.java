package com.alexis.tarotapp.api.repository.listing;

import com.alexis.tarotapp.api.entities.Reading;

import java.util.List;

public class ReadingListingResult extends ListingResult<Reading> {
    public ReadingListingResult(Long count, List<Reading> listing) {
        super(count, listing);
    }
}

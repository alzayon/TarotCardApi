package com.alexis.tarotapp.api.repository.listing;

import com.alexis.tarotapp.api.entities.Meaning;

import java.util.List;

public class MeaningListingResult extends ListingResult<Meaning> {
    public MeaningListingResult(Long count, List<Meaning> listing) {
        super(count, listing);
    }
}

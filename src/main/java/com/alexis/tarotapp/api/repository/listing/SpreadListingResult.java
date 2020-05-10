package com.alexis.tarotapp.api.repository.listing;

import com.alexis.tarotapp.api.entities.Spread;

import java.util.List;

public class SpreadListingResult extends ListingResult<Spread> {
    public SpreadListingResult(Long count, List<Spread> listing) {
        super(count, listing);
    }
}

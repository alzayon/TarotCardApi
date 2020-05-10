package com.alexis.tarotapp.api.repository.listing;

import com.alexis.tarotapp.api.entities.SpreadComponent;

import java.util.List;

public class SpreadComponentListingResult extends ListingResult<SpreadComponent> {
    public SpreadComponentListingResult(Long count, List<SpreadComponent> listing) {
        super(count, listing);
    }
}

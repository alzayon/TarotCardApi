package com.alexis.tarotapp.api.repository.listing;

import com.alexis.tarotapp.api.entities.ReadingSession;

import java.util.List;

public class ReadingSessionListingResult extends ListingResult<ReadingSession> {
    public ReadingSessionListingResult(Long count, List<ReadingSession> listing) {
        super(count, listing);
    }
}

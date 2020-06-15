package com.alexis.tarotapp.api.repository.listing;

import java.util.List;

public abstract class ListingResult<T> {
    private final Long count;
    private final List<T> listing;

    public ListingResult(Long count, List<T> listing) {
        this.count = count;
        this.listing = listing;
    }

    public Long getCount() {
        return count;
    }

    public List<T> getListing() {
        return this.listing;
    }
}

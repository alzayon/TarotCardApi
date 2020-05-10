package com.alexis.tarotapp.api.dto.listing;

import java.util.List;

public class ListingResultDto<T> {
    private final Long count;
    private final List<T> listing;
    private final Throwable error;


    public ListingResultDto(Long count, List<T> listing) {
        this.count = count;
        this.listing = listing;
        this.error = null;
    }

    public ListingResultDto(Throwable error) {
        this.error = error;
        this.listing = null;
        this.count = 0L;
    }

    public Long getCount() {
        return count;
    }

    public List<T> getListing() {
        return listing;
    }

    public Throwable getError() {
        return error;
    }
}

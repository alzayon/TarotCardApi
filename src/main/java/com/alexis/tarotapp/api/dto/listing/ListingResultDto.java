package com.alexis.tarotapp.api.dto.listing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public abstract class ListingResultDto<T> {
    private Long count;
    private List<T> listing;

    public ListingResultDto(Long count, List<T> listing) {
        this.count = count;
        this.listing = listing;
    }

    public Long getCount() {
        return count;
    }

    public List<T> getListing() {
        return this.listing;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setListing(List<T> listing) {
        this.listing = listing;
    }
}

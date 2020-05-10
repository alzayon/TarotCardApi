package com.alexis.tarotapp.api.repository.listing;

import com.alexis.tarotapp.api.entities.Card;

import java.util.List;

public class CardListingResult extends ListingResult<Card> {
    public CardListingResult(Long count, List<Card> listing) {
        super(count, listing);
    }
}

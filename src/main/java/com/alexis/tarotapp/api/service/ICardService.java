package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.CardDto;
import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.CardListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;

/**
 * Created by alzayon on 8/20/2017.
 */
public interface ICardService {
    Result<Card> add(CardDto cardDto);
    Result<Card> update(CardDto cardDto);
    Result<Boolean> delete(int id);
    Result<CardListingResult> list(PaginationParams paginationParams);
    Result<Card> fetch(int id);
}

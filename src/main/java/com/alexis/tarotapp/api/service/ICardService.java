package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

/**
 * Created by alzayon on 8/20/2017.
 */
public interface ICardService {
    Result<Card> add(Card card);
    Result<Card> update(Card card);
    Result<Boolean> delete(Card card);
    Result<List<Card>> list();
    Result<Card> fetch(int id);
}

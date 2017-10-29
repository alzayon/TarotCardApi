package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.CardDto;
import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

/**
 * Created by alzayon on 8/20/2017.
 */
public interface ICardService {
    Result<Card> add(CardDto cardDto);
    Result<Card> update(CardDto cardDto);
    Result<Boolean> delete(int id);
    Result<List<Card>> list();
    Result<Card> fetch(int id);
}

package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.general.result.Result;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alzayon on 6/28/2017.
 */
public interface ICardDao {
    Result<Card> add(Session session, Card card);

    Result<Card> update(Session session, Card card);

    Result<List<Card>> list(Session session);
    Result<Card> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

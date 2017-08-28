package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.general.result.Result;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alzayon on 6/25/2017.
 */
public class CardDao implements ICardDao {

    @Override
    public Result<Card> add(Session session, Card card) {
        session.save(card);
        return new Result<>(card);
    }

    @Override
    public Result<Card> update(Session session, Card card) {
        final Transaction tx = session.beginTransaction();
        final Card reattachedCard = (Card)session.merge(card);
        session.save(reattachedCard);
        tx.commit();
        session.close();
        return new Result<>(reattachedCard);
    }

    @Override
    public Result<List<Card>> list(Session session) {
        final Query query = session.createQuery("from Card");
        final List<Card> cards = query.list();
        return new Result<>(cards);
    }

    @Override
    public Result<Card> fetch(Session session, int id) {
        //https://stackoverflow.com/questions/11089599/hibernate-get-entity-by-id
        final Card card = (Card) session.get(Card.class, id);
        return new Result<>(card);
    }

    @Override
    public Result<Boolean> delete(Session session, int id) {
        final Card card = (Card) session.get(Card.class, id);
        final Transaction tx = session.beginTransaction();
        boolean outcome = false;
        if (card != null) {
            session.delete(card);
            tx.commit();
            outcome = true;
        }
        session.close();
        return new Result<>(outcome);
    }
}

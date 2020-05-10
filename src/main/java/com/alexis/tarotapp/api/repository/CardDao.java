package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.CardListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.HibernateException;
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
        try {
            session.save(card);
            return new Result<>(card);
        } catch (HibernateException ex) {
            final Result<Card> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<Card> update(Session session, Card card) {
        try {
            final Card reattachedCard = (Card)session.merge(card);
            session.save(reattachedCard);
            return new Result<>(reattachedCard);
        } catch (HibernateException ex) {
            final Result<Card> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<CardListingResult> list(Session session, PaginationParams paginationParams) {
        try {
            final Query query = session.createQuery("from Card");
            query.setFirstResult(paginationParams.getStart());
            query.setMaxResults(paginationParams.getLimit());

            String countQ = "Select count (c.id) from Card c";
            Query countQuery = session.createQuery(countQ);
            Long countResults = (Long) countQuery.uniqueResult();

            final List<Card> cards = query.list();
            final CardListingResult cardListingResult = new CardListingResult(countResults, cards);
            return new Result<>(cardListingResult);
        } catch (HibernateException ex) {
            final Result<CardListingResult> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<Card> fetch(Session session, int id) {
        //https://stackoverflow.com/questions/11089599/hibernate-get-entity-by-id
        final Card card = (Card) session.get(Card.class, id);
        return new Result<>(card);
    }

    @Override
    public Result<Boolean> delete(Session session, int id) {
        try {
            final Card card = (Card) session.get(Card.class, id);
            boolean outcome = false;
            if (card != null) {
                session.delete(card);
                outcome = true;
            }
            return new Result<>(outcome);
        } catch (HibernateException ex) {
            final Result<Boolean> result =  new Result<>(false, ex);
            return result;
        }
    }
}

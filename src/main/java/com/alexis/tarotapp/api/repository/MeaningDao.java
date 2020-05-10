package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.MeaningListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alzayon on 8/2/2017.
 */
public class MeaningDao implements IMeaningDao {

    @Override
    public Result<Meaning> add(Session session, Meaning meaning) {
        session.save(meaning);
        return new Result<>(meaning);
    }

    @Override
    public Result<Meaning> update(Session session, Meaning meaning) {
        final Meaning reattachedMeaning = (Meaning)session.merge(meaning);
        session.save(reattachedMeaning);
        return new Result<>(reattachedMeaning);
    }

    @Override
    public Result<MeaningListingResult> list(Session session, PaginationParams paginationParams) {
        try {
            final Query query = session.createQuery("from Meaning");
            query.setFirstResult(paginationParams.getStart());
            query.setMaxResults(paginationParams.getLimit());

            String countQ = "Select count (m.id) from Meaning m";
            Query countQuery = session.createQuery(countQ);
            Long countResults = (Long) countQuery.uniqueResult();

            final List<Meaning> meanings = query.list();
            final MeaningListingResult meaningListingResult = new MeaningListingResult(countResults, meanings);
            return new Result<>(meaningListingResult);
        } catch (HibernateException ex) {
            final Result<MeaningListingResult> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<Meaning> fetch(Session session, int id) {
        //https://stackoverflow.com/questions/11089599/hibernate-get-entity-by-id
        final Meaning meaning = (Meaning) session.get(Meaning.class, id);
        return new Result<>(meaning);
    }

    @Override
    public Result<Boolean> delete(Session session, int id) {
        //http://www.codejava.net/frameworks/hibernate/hibernate-basics-3-ways-to-delete-an-entity-from-the-datastore
        //https://www.mkyong.com/hibernate/hibernate-cascade-example-save-update-delete-and-delete-orphan/
        final Meaning meaning = (Meaning) session.get(Meaning.class, id);
        boolean outcome = false;
        if (meaning != null) {
            session.delete(meaning);
            outcome = true;
        }
        return new Result<>(outcome);
    }
}

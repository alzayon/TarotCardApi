package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Reading;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.ReadingListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ReadingDao implements IReadingDao {

    @Override
    public Result<Reading> add(Session session, Reading reading) {
        session.save(reading);
        return new Result<>(reading);
    }

    @Override
    public Result<Reading> update(Session session, Reading reading) {
        final Reading reattachedReading = (Reading)session.merge(reading);
        session.save(reattachedReading);
        return new Result<>(reattachedReading);
    }

    @Override
    public Result<ReadingListingResult> list(
        Session session,
        PaginationParams paginationParams
    ) {
        try {
            final Query query = session.createQuery("from Reading");
            query.setFirstResult(paginationParams.getStart());
            query.setMaxResults(paginationParams.getLimit());

            String countQ = "Select count (r.id) from Reading r";
            Query countQuery = session.createQuery(countQ);
            Long countResults = (Long) countQuery.uniqueResult();

            final List<Reading> readings = query.list();
            final ReadingListingResult readingListingResult = new ReadingListingResult(countResults, readings);
            return new Result<>(readingListingResult);
        } catch (HibernateException ex) {
            final Result<ReadingListingResult> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<Reading> fetch(Session session, int id) {
        //https://stackoverflow.com/questions/11089599/hibernate-get-entity-by-id
        final Reading reading = (Reading) session.get(Reading.class, id);
        return new Result<>(reading);
    }

    @Override
    public Result<Boolean> delete(Session session, int id) {
        //http://www.codejava.net/frameworks/hibernate/hibernate-basics-3-ways-to-delete-an-entity-from-the-datastore
        //https://www.mkyong.com/hibernate/hibernate-cascade-example-save-update-delete-and-delete-orphan/
        final Reading reading = (Reading) session.get(Reading.class, id);
        boolean outcome = false;
        if (reading != null) {
            session.delete(reading);
            outcome = true;
        }
        return new Result<>(outcome);
    }
}

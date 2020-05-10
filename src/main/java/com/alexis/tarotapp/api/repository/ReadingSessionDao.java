package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.ReadingSession;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.ReadingSessionListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ReadingSessionDao implements IReadingSessionDao {
    @Override
    public Result<ReadingSession> add(Session session, ReadingSession readingSession) {
        try {
            session.save(readingSession);
            return new Result<>(readingSession);
        } catch (HibernateException ex) {
            final Result<ReadingSession> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<ReadingSession> update(Session session, ReadingSession readingSession) {
        try {
            final ReadingSession reattachedReadingSession = (ReadingSession)session.merge(readingSession);
            session.save(reattachedReadingSession);
            return new Result<>(reattachedReadingSession);
        } catch (HibernateException ex) {
            final Result<ReadingSession> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<ReadingSessionListingResult> list(Session session, PaginationParams paginationParams) {
        try {
            final Query query = session.createQuery("from ReadingSession");
            query.setFirstResult(paginationParams.getStart());
            query.setMaxResults(paginationParams.getLimit());

            String countQ = "Select count (rs.id) from ReadingSession rs";
            Query countQuery = session.createQuery(countQ);
            Long countResults = (Long) countQuery.uniqueResult();

            final List<ReadingSession> readings = query.list();
            final ReadingSessionListingResult readingSessionListingResult = new ReadingSessionListingResult(countResults, readings);
            return new Result<>(readingSessionListingResult);
        } catch (HibernateException ex) {
            final Result<ReadingSessionListingResult> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<ReadingSession> fetch(Session session, int id) {
        //https://stackoverflow.com/questions/11089599/hibernate-get-entity-by-id
        final ReadingSession readingSession = (ReadingSession) session.get(ReadingSession.class, id);
        return new Result<>(readingSession);
    }

    @Override
    public Result<Boolean> delete(Session session, int id) {
        try {
            final ReadingSession readingSession = (ReadingSession) session.get(ReadingSession.class, id);
            boolean outcome = false;
            if (readingSession != null) {
                session.delete(readingSession);
                outcome = true;
            }
            return new Result<>(outcome);
        } catch (HibernateException ex) {
            final Result<Boolean> result =  new Result<>(false, ex);
            return result;
        }
    }
}

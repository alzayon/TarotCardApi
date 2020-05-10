package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.SpreadListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class SpreadDao implements ISpreadDao {
    @Override
    public Result<Spread> add(Session session, Spread spread) {
        try {
            session.save(spread);
            return new Result<>(spread);
        } catch (HibernateException ex) {
            final Result<Spread> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<Spread> update(Session session, Spread spread) {
        try {
            final Spread reattachedSpread = (Spread)session.merge(spread);
            session.save(reattachedSpread);
            return new Result<>(reattachedSpread);
        } catch (HibernateException ex) {
            final Result<Spread> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<SpreadListingResult> list(Session session, PaginationParams paginationParams) {
        try {
            final Query query = session.createQuery("from Spread");
            query.setFirstResult(paginationParams.getStart());
            query.setMaxResults(paginationParams.getLimit());

            String countQ = "Select count (s.id) from Spread s";
            Query countQuery = session.createQuery(countQ);
            Long countResults = (Long) countQuery.uniqueResult();

            final List<Spread> spreadComponents = query.list();
            final SpreadListingResult spreadComponentsListingResult = new SpreadListingResult(countResults, spreadComponents);
            return new Result<>(spreadComponentsListingResult);
        } catch (HibernateException ex) {
            final Result<SpreadListingResult> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<Spread> fetch(Session session, int id) {
        //https://stackoverflow.com/questions/11089599/hibernate-get-entity-by-id
        final Spread spread = (Spread) session.get(Spread.class, id);
        return new Result<>(spread);
    }

    @Override
    public Result<Boolean> delete(Session session, int id) {
        try {
            final Spread spread = (Spread) session.get(Spread.class, id);
            boolean outcome = false;
            if (spread != null) {
                session.delete(spread);
                outcome = true;
            }
            return new Result<>(outcome);
        } catch (HibernateException ex) {
            final Result<Boolean> result =  new Result<>(false, ex);
            return result;
        }
    }
}

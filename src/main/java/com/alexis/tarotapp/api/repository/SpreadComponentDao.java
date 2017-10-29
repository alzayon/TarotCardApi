package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.SpreadComponent;
import com.alexis.tarotapp.api.general.result.Result;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class SpreadComponentDao implements ISpreadComponentDao {
    @Override
    public Result<SpreadComponent> add(Session session, SpreadComponent spreadComponent) {
        try {
            session.save(spreadComponent);
            return new Result<>(spreadComponent);
        } catch (HibernateException ex) {
            final Result<SpreadComponent> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<SpreadComponent> update(Session session, SpreadComponent spreadComponent) {
        try {
            final SpreadComponent reattachedSpread = (SpreadComponent)session.merge(spreadComponent);
            session.save(reattachedSpread);
            return new Result<>(reattachedSpread);
        } catch (HibernateException ex) {
            final Result<SpreadComponent> result =  new Result<>(null, ex);
            return result;
        }
    }

    @Override
    public Result<List<SpreadComponent>> list(Session session) {
        final Query query = session.createQuery("from SpreadComponent");
        final List<SpreadComponent> spreads = query.list();
        return new Result<>(spreads);
    }

    @Override
    public Result<SpreadComponent> fetch(Session session, int id) {
        //https://stackoverflow.com/questions/11089599/hibernate-get-entity-by-id
        final SpreadComponent spreadComponent = (SpreadComponent) session.get(SpreadComponent.class, id);
        return new Result<>(spreadComponent);
    }

    @Override
    public Result<Boolean> delete(Session session, int id) {
        try {
            final SpreadComponent spreadComponent = (SpreadComponent) session.get(SpreadComponent.class, id);
            boolean outcome = false;
            if (spreadComponent != null) {
                session.delete(spreadComponent);
                outcome = true;
            }
            return new Result<>(outcome);
        } catch (HibernateException ex) {
            final Result<Boolean> result =  new Result<>(false, ex);
            return result;
        }
    }
}

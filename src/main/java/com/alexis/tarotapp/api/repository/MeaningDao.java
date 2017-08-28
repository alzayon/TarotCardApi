package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.general.result.Result;
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
        final Transaction tx = session.beginTransaction();
        session.save(meaning);
        tx.commit();
        session.close();
        return new Result<>(meaning);
    }

    @Override
    public Result<Meaning> update(Session session, Meaning meaning) {
        final Transaction tx = session.beginTransaction();
        final Meaning reattachedMeaning = (Meaning)session.merge(meaning);
        session.save(reattachedMeaning);
        tx.commit();
        session.close();
        return new Result<>(reattachedMeaning);
    }

    @Override
    public Result<List<Meaning>> list(Session session) {
        final Query query = session.createQuery("from Meaning");
        final List<Meaning> meanings = query.list();
        return new Result<>(meanings);
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
        final Transaction tx = session.beginTransaction();
        boolean outcome = false;
        if (meaning != null) {
            session.delete(meaning);
            tx.commit();
            outcome = true;
        }
        session.close();
        return new Result<>(outcome);
    }
}

package com.alexis.tarotapp.api.unitofwork;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by alzayon on 8/10/2017.
 */
public class HIbernateUnitOfWork implements IUnitOfWork {

    private final Session session;
    private Transaction transaction;

    public HIbernateUnitOfWork(Session session) {
        this.session = session;
    }

    @Override
    public void start() {
        transaction = session.beginTransaction();
    }

    @Override
    public void commit() {
        if (transaction != null) {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void rollback() {
        if (transaction != null) {
            transaction.rollback();
            session.close();
        }
    }
}

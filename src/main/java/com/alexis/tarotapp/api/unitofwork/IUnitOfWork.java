package com.alexis.tarotapp.api.unitofwork;

/**
 * Created by alzayon on 8/10/2017.
 */
public interface IUnitOfWork {
    public void start();
    public void commit();
    public void rollback();
}

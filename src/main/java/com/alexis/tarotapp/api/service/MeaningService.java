package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.IMeaningDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

import java.util.List;

public class MeaningService implements IMeaningService {
    private final IMeaningDao meaningDao;

    public MeaningService(IMeaningDao meaningDao) {
        this.meaningDao = meaningDao;
    }

    @Override
    public Result<Meaning> add(Meaning meaning) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Meaning> result = meaningDao.add(session, meaning);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Meaning> update(Meaning meaning) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Meaning> result = meaningDao.update(session, meaning);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(Meaning meaning) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = meaningDao.delete(session, meaning.getId());
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<List<Meaning>> list() {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<List<Meaning>> result = meaningDao.list(session);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Meaning> fetch(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Meaning> result = meaningDao.fetch(session, id);
        unitOfWork.commit();

        return result;
    }
}

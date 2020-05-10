package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.MeaningDto;
import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.entities.helper.EntityHelper;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.IMeaningDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.repository.listing.MeaningListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

public class MeaningService implements IMeaningService {
    private final IMeaningDao meaningDao;

    public MeaningService(IMeaningDao meaningDao) {
        this.meaningDao = meaningDao;
    }

    @Override
    public Result<Meaning> add(MeaningDto meaningDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Meaning meaning = EntityHelper.toEntity(session, meaningDto);
        final Result<Meaning> result = meaningDao.add(session, meaning);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Meaning> update(MeaningDto meaningDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Meaning meaning = EntityHelper.toEntity(session, meaningDto);
        final Result<Meaning> result = meaningDao.update(session, meaning);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = meaningDao.delete(session, id);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<MeaningListingResult> list(PaginationParams paginationParams) {
        final Session session = SessionUtil.getSession();

        final Result<MeaningListingResult> result = meaningDao.list(session, paginationParams);

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

package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.SpreadDto;
import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.entities.helper.EntityHelper;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.ISpreadDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.repository.listing.SpreadListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

import java.util.List;

public class SpreadService implements ISpreadService {

    private final ISpreadDao spreadDao;

    public SpreadService(ISpreadDao spreadDao) {
        this.spreadDao = spreadDao;
    }

    @Override
    public Result<Spread> add(SpreadDto cardDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Spread card = EntityHelper.toEntity(session, cardDto);
        final Result<Spread> result = spreadDao.add(session, card);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Spread> update(SpreadDto cardDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Spread spread = EntityHelper.toEntity(session, cardDto);
        final Result<Spread> result = spreadDao.update(session, spread);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = spreadDao.delete(session, id);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<SpreadListingResult> list(PaginationParams paginationParams) {
        final Session session = SessionUtil.getSession();

        final Result<SpreadListingResult> result = spreadDao.list(session, paginationParams);

        return result;
    }

    @Override
    public Result<Spread> fetch(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Spread> result = spreadDao.fetch(session, id);
        unitOfWork.commit();

        return result;
    }
}

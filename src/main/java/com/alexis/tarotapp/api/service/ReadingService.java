package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.ReadingDto;
import com.alexis.tarotapp.api.entities.Reading;
import com.alexis.tarotapp.api.entities.helper.EntityHelper;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.IReadingDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.repository.listing.ReadingListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

public class ReadingService implements IReadingService {
    private final IReadingDao readingDao;

    public ReadingService(IReadingDao readingDao) {
        this.readingDao = readingDao;
    }

    @Override
    public Result<Reading> add(ReadingDto readingDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Reading reading = EntityHelper.toEntity(session, readingDto);
        final Result<Reading> result = readingDao.add(session, reading);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Reading> update(ReadingDto readingDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Reading reading = EntityHelper.toEntity(session, readingDto);
        final Result<Reading> result = readingDao.update(session, reading);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = readingDao.delete(session, id);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<ReadingListingResult> list(PaginationParams paginationParams) {
        final Session session = SessionUtil.getSession();

        final Result<ReadingListingResult> result = readingDao.list(session, paginationParams);

        return result;
    }

    @Override
    public Result<Reading> fetch(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Reading> result = readingDao.fetch(session, id);
        unitOfWork.commit();

        return result;
    }
}

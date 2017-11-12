package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.ReadingSessionDto;
import com.alexis.tarotapp.api.entities.ReadingSession;
import com.alexis.tarotapp.api.entities.helper.EntityHelper;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.IReadingSessionDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

import java.util.List;

public class ReadingSessionService implements IReadingSessionService {
    private final IReadingSessionDao readingSessionDao;

    public ReadingSessionService(IReadingSessionDao readingSessionDao) {
        this.readingSessionDao = readingSessionDao;
    }

    @Override
    public Result<ReadingSession> add(ReadingSessionDto readingSessionDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final ReadingSession readingSession = EntityHelper.toEntity(session, readingSessionDto);
        final Result<ReadingSession> result = readingSessionDao.add(session, readingSession);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<ReadingSession> update(ReadingSessionDto readingSessionDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final ReadingSession readingSession = EntityHelper.toEntity(session, readingSessionDto);
        final Result<ReadingSession> result = readingSessionDao.update(session, readingSession);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = readingSessionDao.delete(session, id);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<List<ReadingSession>> list() {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<List<ReadingSession>> result = readingSessionDao.list(session);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<ReadingSession> fetch(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<ReadingSession> result = readingSessionDao.fetch(session, id);
        unitOfWork.commit();

        return result;
    }
}

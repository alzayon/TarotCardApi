package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.SpreadComponentDto;
import com.alexis.tarotapp.api.entities.SpreadComponent;
import com.alexis.tarotapp.api.entities.helper.EntityHelper;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.ISpreadComponentDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

import java.util.List;

public class SpreadComponentService implements ISpreadComponentService {
    private final ISpreadComponentDao spreadComponentDao;

    public SpreadComponentService(ISpreadComponentDao spreadComponentDao) {
        this.spreadComponentDao = spreadComponentDao;
    }

    @Override
    public Result<SpreadComponent> add(SpreadComponentDto spreadComponentDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final SpreadComponent spreadComponent = EntityHelper.toEntity(session, spreadComponentDto);
        final Result<SpreadComponent> result = spreadComponentDao.add(session, spreadComponent);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<SpreadComponent> update(SpreadComponentDto spreadComponentDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final SpreadComponent spreadComponent = EntityHelper.toEntity(session, spreadComponentDto);
        final Result<SpreadComponent> result = spreadComponentDao.update(session, spreadComponent);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = spreadComponentDao.delete(session, id);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<List<SpreadComponent>> list() {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<List<SpreadComponent>> result = spreadComponentDao.list(session);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<SpreadComponent> fetch(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<SpreadComponent> result = spreadComponentDao.fetch(session, id);
        unitOfWork.commit();

        return result;
    }
}

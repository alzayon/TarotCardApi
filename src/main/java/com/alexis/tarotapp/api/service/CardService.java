package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.ICardDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alzayon on 8/20/2017.
 */
public class CardService implements ICardService {

    private final ICardDao cardDao;

    public CardService(ICardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Override
    public Result<Card> add(Card card) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Card> result = cardDao.add(session, card);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Card> update(Card card) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Card> result = cardDao.update(session, card);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(Card card) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = cardDao.delete(session, card.getId());
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<List<Card>> list() {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<List<Card>> result = cardDao.list(session);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Card> fetch(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Card> result = cardDao.fetch(session, id);
        unitOfWork.commit();

        return result;
    }
}

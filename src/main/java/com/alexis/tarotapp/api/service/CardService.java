package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.CardDto;
import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.entities.helper.EntityHelper;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.ICardDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.repository.listing.CardListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

/**
 * Created by alzayon on 8/20/2017.
 */
public class CardService implements ICardService {

    private final ICardDao cardDao;

    public CardService(ICardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Override
    public Result<Card> add(CardDto cardDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Card card = EntityHelper.toEntity(session, cardDto);
        final Result<Card> result = cardDao.add(session, card);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Card> update(CardDto cardDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Card card = EntityHelper.toEntity(session, cardDto);
        final Result<Card> result = cardDao.update(session, card);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = cardDao.delete(session, id);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<CardListingResult> list(PaginationParams paginationParams) {
        final Session session = SessionUtil.getSession();

        final Result<CardListingResult> result = cardDao.list(session, paginationParams);

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

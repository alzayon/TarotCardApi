package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.ICategoryDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

import java.util.List;

public class CategoryService implements ICategoryService {

    private final ICategoryDao categoryDao;

    public CategoryService(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Result<Category> add(Category category) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Category> result = categoryDao.add(session, category);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Category> update(Category category) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Category> result = categoryDao.update(session, category);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(Category category) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = categoryDao.delete(session, category.getId());
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<List<Category>> list() {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<List<Category>> result = categoryDao.list(session);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Category> fetch(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Category> result = categoryDao.fetch(session, id);
        unitOfWork.commit();

        return result;
    }
}

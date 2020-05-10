package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.CategoryDto;
import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.entities.helper.EntityHelper;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.ICategoryDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.repository.listing.CategoryListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import com.alexis.tarotapp.api.unitofwork.HIbernateUnitOfWork;
import com.alexis.tarotapp.api.unitofwork.IUnitOfWork;
import org.hibernate.Session;

public class CategoryService implements ICategoryService {

    private final ICategoryDao categoryDao;

    public CategoryService(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Result<Category> add(CategoryDto categoryDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Category category = EntityHelper.toEntity(session, categoryDto);
        final Result<Category> result = categoryDao.add(session, category);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Category> update(CategoryDto categoryDto) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Category category = EntityHelper.toEntity(session, categoryDto);
        final Result<Category> result = categoryDao.update(session, category);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<Boolean> delete(int id) {
        final Session session = SessionUtil.getSession();
        final IUnitOfWork unitOfWork = new HIbernateUnitOfWork(session);

        unitOfWork.start();
        final Result<Boolean> result = categoryDao.delete(session, id);
        unitOfWork.commit();

        return result;
    }

    @Override
    public Result<CategoryListingResult> list(PaginationParams paginationParams) {
        final Session session = SessionUtil.getSession();

        final Result<CategoryListingResult> result = categoryDao.list(session, paginationParams);

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

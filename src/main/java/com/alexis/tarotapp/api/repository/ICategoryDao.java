package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.CategoryListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alzayon on 8/1/2017.
 */
public interface ICategoryDao {
    Result<Category> add(Session session, Category category);

    Result<Category> update(Session session, Category category);

    Result<CategoryListingResult> list(Session session, PaginationParams paginationParams);
    Result<Category> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

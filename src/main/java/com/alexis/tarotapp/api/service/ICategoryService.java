package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.CategoryDto;
import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.CategoryListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;

public interface ICategoryService {
    Result<Category> add(CategoryDto categoryDto);
    Result<Category> update(CategoryDto categoryDto);
    Result<Boolean> delete(int id);
    Result<CategoryListingResult> list(PaginationParams paginationParams);
    Result<Category> fetch(int id);
}

package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.CategoryDto;
import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

public interface ICategoryService {
    Result<Category> add(CategoryDto categoryDto);
    Result<Category> update(CategoryDto categoryDto);
    Result<Boolean> delete(int id);
    Result<List<Category>> list();
    Result<Category> fetch(int id);
}

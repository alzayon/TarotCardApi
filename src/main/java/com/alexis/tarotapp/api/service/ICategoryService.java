package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

public interface ICategoryService {
    Result<Category> add(Category category);
    Result<Category> update(Category category);
    Result<Boolean> delete(Category category);
    Result<List<Category>> list();
    Result<Category> fetch(int id);
}

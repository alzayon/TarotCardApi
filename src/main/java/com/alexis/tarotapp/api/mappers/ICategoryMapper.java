package com.alexis.tarotapp.api.mappers;

import com.alexis.tarotapp.api.dto.CategoryDto;
import com.alexis.tarotapp.api.dto.listing.CategoryListingResultDto;
import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.repository.listing.CategoryListingResult;
import org.hibernate.Session;
import org.mapstruct.Mapper;
@Mapper
public interface ICategoryMapper {
    Category dtoToEntity(Session session, CategoryDto categoryDto);
    CategoryDto entityToDto(Category category);

    CategoryListingResultDto entityToDto(CategoryListingResult categoryResult);
}

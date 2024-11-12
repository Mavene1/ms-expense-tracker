package com.mavene.expensetracker.mapper;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.entity.Category;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getCategoryId(),
                category.getUserId(),
                category.getTitle(),
                category.getDescription()
        );
    }
    public static Category toCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getCategoryId(),
                categoryDto.getUserId(),
                categoryDto.getTitle(),
                categoryDto.getDescription()
        );
    }
}

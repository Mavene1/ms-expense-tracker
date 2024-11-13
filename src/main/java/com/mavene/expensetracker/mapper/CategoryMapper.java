package com.mavene.expensetracker.mapper;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.entity.Category;

public class CategoryMapper {

//    // Method to convert Category entity to CategoryDto
//    public static CategoryDto toCategoryDto(Category category) {
//        return new CategoryDto(
//                category.getCategoryId(),
//                category.getUser(),
//                category.getTitle(),
//                category.getDescription()
//        );
//    }
//
//    // Method to convert CategoryDto to Category entity (optional, based on needs)
//    public static Category toCategory(CategoryDto categoryDto) {
//        return new Category(
//                categoryDto.getCategoryId(),
//                categoryDto.getUserId(),
//                categoryDto.getTitle(),
//                categoryDto.getDescription()
//        );
//    }

    public static Category toCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        // The User should be set later in the service (user association)
        return category;
    }

    public static CategoryDto toCategoryDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setUserId(category.getUserId().getUserId()); // Map userId from the User object); // Map userId from the User object
        return categoryDto;
    }
}


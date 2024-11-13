package com.mavene.expensetracker.services;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.exception.EtBadRequestException;
import com.mavene.expensetracker.exception.EtResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    //get all categories
    List<CategoryDto> getAllCategories(Integer userId);

    //get category by id
    CategoryDto getCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    //create category
    CategoryDto createCategory(Integer userId, CategoryDto categoryDto) throws EtBadRequestException;

    //update category
    CategoryDto updateCategory(Integer userId, Integer categoryId, CategoryDto categoryDto) throws EtBadRequestException;

    //delete category
    void deleteCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

}

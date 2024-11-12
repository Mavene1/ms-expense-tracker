package com.mavene.expensetracker.services.impl;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.entity.Category;
import com.mavene.expensetracker.exception.EtBadRequestException;
import com.mavene.expensetracker.exception.EtResourceNotFoundException;
import com.mavene.expensetracker.mapper.CategoryMapper;
import com.mavene.expensetracker.repository.CategoryRepository;
import com.mavene.expensetracker.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories(Integer userId) {
        List<Category> categories = categoryRepository.findAll();
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws EtBadRequestException {
        Category category = CategoryMapper.toCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Integer userId, Integer categoryId, CategoryDto categoryDto) throws EtBadRequestException {
        return null;
    }

    @Override
    public void deleteCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException {

    }
}

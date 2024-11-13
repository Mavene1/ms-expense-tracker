package com.mavene.expensetracker.services.impl;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.entity.Category;
import com.mavene.expensetracker.entity.User;
import com.mavene.expensetracker.exception.EtBadRequestException;
import com.mavene.expensetracker.exception.EtResourceNotFoundException;
import com.mavene.expensetracker.mapper.CategoryMapper;
import com.mavene.expensetracker.repository.CategoryRepository;
import com.mavene.expensetracker.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public CategoryDto createCategory(Integer userId, CategoryDto categoryDto) throws EtBadRequestException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        Category category = CategoryMapper.toCategory(categoryDto);
        category.setUserId(user);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        Category category = categoryRepository.findById(categoryId)
                .filter(category1 -> category1.getUserId().equals(user))
                .orElseThrow(() -> new EtResourceNotFoundException("Category not found with id: " + categoryId));
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        List<Category> categories = categoryRepository.findAllByUserId(user);
        return categories.stream().map(CategoryMapper::toCategoryDto).toList();
    }

    @Override
    public CategoryDto updateCategory(Integer userId, Integer categoryId, CategoryDto categoryDto) throws EtBadRequestException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EtBadRequestException("User not found with id: " + userId));
        Category category = categoryRepository.findById(categoryId)
                .filter(category1 -> category1.getUserId().equals(user))
                .orElseThrow(() -> new EtResourceNotFoundException("Category not found with id: " + categoryId));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public void deleteCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException {

    }
}

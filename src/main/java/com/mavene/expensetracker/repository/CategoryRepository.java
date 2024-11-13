package com.mavene.expensetracker.repository;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.entity.Category;
import com.mavene.expensetracker.entity.User;
import com.mavene.expensetracker.exception.EtBadRequestException;
import com.mavene.expensetracker.exception.EtResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByUserId(User userId) throws EtResourceNotFoundException;
//    CategoryDto findById(Integer categoryId, Integer userId) throws EtResourceNotFoundException;
//    CategoryDto create(Integer userId, CategoryDto categoryDto) throws EtBadRequestException;
//    CategoryDto update(Integer userId, Integer categoryId, CategoryDto categoryDto) throws EtBadRequestException;
//    void delete(Integer userId, Integer categoryId);
}

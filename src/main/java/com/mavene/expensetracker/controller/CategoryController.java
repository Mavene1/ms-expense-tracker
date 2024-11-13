package com.mavene.expensetracker.controller;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.entity.Category;
import com.mavene.expensetracker.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    //createCategory
    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDto> createCategory(HttpServletRequest request, @RequestBody CategoryDto categoryDto){
        Integer userId = (Integer) request.getAttribute("userId");
        CategoryDto createdCategory = categoryService.createCategory(userId, categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
    //getCategoryById
    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(HttpServletRequest request, @PathVariable Integer categoryId){
        Integer userId = (Integer) request.getAttribute("userId");
        CategoryDto category = categoryService.getCategoryById(userId, categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    //getAllCategories
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");
        List<CategoryDto> categories = categoryService.getAllCategories(userId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    //updateCategory
    @PutMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(HttpServletRequest request, @PathVariable Integer categoryId, @RequestBody CategoryDto categoryDto){
        Integer userId = (Integer) request.getAttribute("userId");
        CategoryDto updatedCategory = categoryService.updateCategory(userId, categoryId, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
    //deleteCategory
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<String> deleteCategory(HttpServletRequest request, @PathVariable Integer categoryId){
        Integer userId = (Integer) request.getAttribute("userId");
        categoryService.deleteCategoryWithAllTransactions(userId, categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}

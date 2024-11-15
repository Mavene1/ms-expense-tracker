package com.mavene.expensetracker.controller;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.services.CategoryService;
import com.mavene.expensetracker.dto.ResponseStructure;
import com.mavene.expensetracker.utils.ResponseUtil;
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
        return new ResponseEntity<>(createdCategory, HttpStatus.OK);
    }
    //getCategoryById
    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<ResponseStructure<CategoryDto>> getCategoryById(HttpServletRequest request, @PathVariable Integer categoryId){
        Integer userId = (Integer) request.getAttribute("userId");
        CategoryDto category = categoryService.getCategoryById(userId, categoryId);
        ResponseStructure<CategoryDto> response = ResponseUtil.createResponse(
                200,
                "Success",
                "Category fetched successfully",
                category
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //getAllCategories
    @GetMapping("/getAllCategories")
    public ResponseEntity<ResponseStructure<List<CategoryDto>>> getAllCategories(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("userId");
        List<CategoryDto> categories = categoryService.getAllCategories(userId);
        ResponseStructure<List<CategoryDto>> response = ResponseUtil.createResponse(
                200,
                "Success",
                "All Categories fetched successfully",
                categories
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
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

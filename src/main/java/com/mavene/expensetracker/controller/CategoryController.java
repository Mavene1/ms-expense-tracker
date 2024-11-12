package com.mavene.expensetracker.controller;

import com.mavene.expensetracker.dto.CategoryDto;
import com.mavene.expensetracker.services.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public String getAllCategories(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        return "Authenticated User: "+ userId;
    }

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(createdCategory);

    }
}

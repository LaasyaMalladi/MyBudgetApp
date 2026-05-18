package com.mybudgetapp.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybudgetapp.backend.entity.Category;
import com.mybudgetapp.backend.service.CategoryService;
@RestController
@RequestMapping("/api/categories")
public class CategoryContoller {

    private final CategoryService categoryService;

    public CategoryContoller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/user/{userId}")
    public List<Category> getCategoriesByUserId(@PathVariable Long userId) {
        return categoryService.getCategoriesByUser(userId);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

package com.kk.eshop.controller;

import com.kk.eshop.exceptions.ResourceNotFoundException;
import com.kk.eshop.model.Category;
import com.kk.eshop.response.ApiResponse;
import com.kk.eshop.service.product.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("{api.prefix}/categories")
public class CategoryController {

    private final ICategoryService iCategoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categoryList = iCategoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found!", categoryList));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try {
            Category theCategory = iCategoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("success", theCategory));
        } catch (Exception e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId) {
        try {
            Category theCategory = iCategoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Found!", theCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
        try {
            Category theCategory = iCategoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Found!", theCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long categoryId) {
        try {
            iCategoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Found!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        try {
            Category updatedCategory = iCategoryService.updateCategoryById(category, categoryId);
            return ResponseEntity.ok(new ApiResponse("Update successful!", updatedCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }



}

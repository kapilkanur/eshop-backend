package com.kk.eshop.controller;

import com.kk.eshop.exceptions.ResourceNotFoundException;
import com.kk.eshop.model.Category;
import com.kk.eshop.response.ApiResponse;
import com.kk.eshop.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@RequiredArgsConstructor
@RestController
@RequestMapping("{api.prefix}/categories")
public final class CategoryController {

    private final ICategoryService iCategoryService;

    /**
     * Get all categories.
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categoryList = iCategoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Found!", categoryList));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error", INTERNAL_SERVER_ERROR));
        }
    }

    /**
     * Add a category.
     * @param category category
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody final Category category) {
        try {
            Category theCategory = iCategoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("success", theCategory));
        } catch (Exception e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    /**
     * Get category by Id.
     * @param categoryId category id
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable final Long categoryId) {
        try {
            Category theCategory = iCategoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Found!", theCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    /**
     * Get category by name.
     * @param name category name
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable final String name) {
        try {
            Category theCategory = iCategoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Found!", theCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    /**
     * Delete category by Id.
     * @param categoryId category id
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable final Long categoryId) {
        try {
            iCategoryService.deleteCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("Found!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    /**
     * Update category.
     * @param categoryId category id
     * @param category category
     * @return {@link ResponseEntity<ApiResponse>}
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable final Long categoryId, @RequestBody final Category category) {
        try {
            Category updatedCategory = iCategoryService.updateCategoryById(category, categoryId);
            return ResponseEntity.ok(new ApiResponse("Update successful!", updatedCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }



}

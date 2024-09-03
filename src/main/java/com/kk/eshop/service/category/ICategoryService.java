// CHECKSTYLE:OFF
package com.kk.eshop.service.category;

import com.kk.eshop.model.Category;

import java.util.List;

public interface ICategoryService {

    Category addCategory(Category category);
    Category getCategoryById(Long categoryId);
    Category getCategoryByName(String categoryName);
    Category updateCategoryById(Category category, Long categoryId);
    List<Category> getAllCategories();
    void deleteCategoryById(Long categoryId);

}

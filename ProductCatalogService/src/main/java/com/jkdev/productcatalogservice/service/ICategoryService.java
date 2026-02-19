package com.jkdev.productcatalogservice.service;

import com.jkdev.productcatalogservice.dto.CategoryDto;
import com.jkdev.productcatalogservice.entity.Category;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Category operations
 */
public interface ICategoryService {

    /**
     * Get all categories
     */
    List<CategoryDto> getAllCategories();

    /**
     * Get category by ID
     */
    CategoryDto getCategoryById(Long id);

    /**
     * Create a new category
     */
    CategoryDto createCategory(CategoryDto categoryDto);

    /**
     * Update an existing category
     */
    boolean updateCategory(Long id, CategoryDto categoryDto);

    /**
     * Delete a category
     */
    boolean deleteCategory(Long id);

    /**
     * Find category by name
     */
    CategoryDto findByName(String name);
}


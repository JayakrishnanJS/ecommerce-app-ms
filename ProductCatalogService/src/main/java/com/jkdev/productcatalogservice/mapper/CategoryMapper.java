package com.jkdev.productcatalogservice.mapper;

import com.jkdev.productcatalogservice.dto.CategoryDto;
import com.jkdev.productcatalogservice.entity.Category;

/**
 * Mapper class for Category entity and DTOs
 * Handles conversion between DTO and Entity objects using @Builder pattern
 */
public class CategoryMapper {

    /**
     * Map Category entity to CategoryDTO using builder pattern
     * @param category Category entity
     * @return CategoryDTO object
     */
    public static CategoryDto mapToCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    /**
     * Map CategoryDTO to Category entity using builder pattern
     * @param categoryDTO CategoryDTO object
     * @return Category entity
     */
    public static Category mapToCategory(CategoryDto categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        return Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
    }
}


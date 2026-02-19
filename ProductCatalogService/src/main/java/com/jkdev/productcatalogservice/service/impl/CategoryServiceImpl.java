package com.jkdev.productcatalogservice.service.impl;

import com.jkdev.productcatalogservice.dto.CategoryDto;
import com.jkdev.productcatalogservice.entity.Category;
import com.jkdev.productcatalogservice.exception.CategoryAlreadyExistsException;
import com.jkdev.productcatalogservice.exception.ResourceNotFoundException;
import com.jkdev.productcatalogservice.mapper.CategoryMapper;
import com.jkdev.productcatalogservice.repository.CategoryRepository;
import com.jkdev.productcatalogservice.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of CategoryService
 */
@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        // convert List<Category> to List<CategoryDto> using the mapper which has builder pattern of entity to convert the entity to DTO
        return categories.stream()
                .map(CategoryMapper::mapToCategoryDTO)
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", id)
        );
        CategoryDto categoryResponseDto = CategoryMapper.mapToCategoryDTO(category); // Map Category entity to CategoryDto using the mapper which has builder pattern of entity to convert the entity to DTO
        return categoryResponseDto;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryRequestDto) {
        Optional<Category> optionalCategory = categoryRepository.findByNameIgnoreCase(categoryRequestDto.getName());
        if(optionalCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("Category with name " + categoryRequestDto.getName() + " already exists");
        }
        Category category = CategoryMapper.mapToCategory(categoryRequestDto);
        Category createdCategory =  categoryRepository.save(category);
        CategoryDto categoryResponseDto = CategoryMapper.mapToCategoryDTO(createdCategory);
        return categoryResponseDto;
    }

    @Override
    public boolean updateCategory(Long id, CategoryDto categoryRequestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", categoryRequestDto.getId())
        );
        Category updatedCategory = CategoryMapper.mapToCategory(categoryRequestDto);
        updatedCategory.setId(id); // Ensure the ID is set for the updated category to avoid creating a new category instead of updating the existing one
        // mapToCategory will create a new Category object with the same name and description but with a null ID, so we need to set the ID of the updated category to the existing category's ID to ensure that we are updating the existing category instead of creating a new one
        Category savedCategory = categoryRepository.save(updatedCategory);
        return savedCategory != null; // Return true if the category was successfully updated, false otherwise
    }

    @Override
    public boolean deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", id)
        );
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public CategoryDto findByName(String name) {

        Category category = categoryRepository.findByNameIgnoreCase(name).orElseThrow(
                () -> new ResourceNotFoundException("Category", "name", name)
        );
        CategoryDto categoryDto = CategoryMapper.mapToCategoryDTO(category);
        return categoryDto;
    }
}


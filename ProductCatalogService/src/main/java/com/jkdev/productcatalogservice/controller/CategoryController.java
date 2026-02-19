package com.jkdev.productcatalogservice.controller;

import com.jkdev.productcatalogservice.constants.CategoryConstants;
import com.jkdev.productcatalogservice.dto.CategoryDto;
import com.jkdev.productcatalogservice.dto.ResponseDto;
import com.jkdev.productcatalogservice.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Category Management
 * Base URL: /api/v1/categories
 */
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * Get all categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity
                .ok(categories);
    }

    /**
     * Get category by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryResponseDto = categoryService.getCategoryById(id);
        return ResponseEntity
                .ok(categoryResponseDto);
    }

    /**
     * Create a new category
     */
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryRequestDto) {
        CategoryDto categoryResponseDto = categoryService.createCategory(categoryRequestDto);
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(categoryResponseDto);
    }

    /**
     * Update an existing category
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryRequestDto) {
        // PathVariable id is used to identify the category(resource) to update, while the request body contains the new data for the category.
        boolean isUpdated = categoryService.updateCategory(id, categoryRequestDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CategoryConstants.STATUS_200, CategoryConstants.MESSAGE_200_UPDATE));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CategoryConstants.STATUS_417, CategoryConstants.MESSAGE_417_UPDATE));
        }
    }

    /**
     * Delete a category
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable Long id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CategoryConstants.STATUS_200, CategoryConstants.MESSAGE_200_DELETE));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CategoryConstants.STATUS_417, CategoryConstants.MESSAGE_417_DELETE));
        }
    }
}

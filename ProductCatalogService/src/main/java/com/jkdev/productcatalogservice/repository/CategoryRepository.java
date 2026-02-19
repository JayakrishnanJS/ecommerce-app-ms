package com.jkdev.productcatalogservice.repository;

import com.jkdev.productcatalogservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Find category by name (case-insensitive, for FakeStore sync)
     */
    Optional<Category> findByNameIgnoreCase(String name);

    /**
     * Find categories by name containing (case-insensitive)
     * Use case: Search categories by partial name match
     */
    List<Category> findByNameContainingIgnoreCase(String name);

    /**
     * Check if category exists by name
     * Use case: Prevent duplicate category creation
     */
    boolean existsByName(String name);

    /**
     * Get category description by ID using HQL query
     * Use case: Fetch only description (memory efficient)
     */
    @Query("SELECT c.description FROM Category c WHERE c.id = :id")
    String findDescriptionById(@Param("id") Long id);

    /**
     * Find all categories ordered by name
     * Use case: List categories alphabetically
     */
    List<Category> findAllByOrderByName();

    /**
     * Count products in a category
     * Use case: Get product count for category
     */
    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId")
    long countProductsInCategory(@Param("categoryId") Long categoryId);

    /**
     * Check if category exists by name (case-insensitive)
     * Use case: Flexible duplicate prevention
     */
    boolean existsByNameIgnoreCase(String name);
}




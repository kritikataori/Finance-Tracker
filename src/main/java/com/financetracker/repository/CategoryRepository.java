package com.financetracker.repository;

import com.financetracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //find all system categories
    @Query("SELECT c FROM Category c WHERE c.user IS NULL AND c.isActive = true")
    List<Category> findAllSystemCategories();

    //find all custom categories for a user
    @Query("SELECT c FROM Category c WHERE c.user.id = :userId AND c.isActive = true")
    List<Category> findAllCategoriesByAUser(@Param("userId") Long userId);

    //find categories by category type for a user
    @Query("SELECT c FROM Category c WHERE (c.user IS NULL OR c.user.id = :userId) AND c.type = :type AND c.isActive = true")
    List<Category> findCategoriesByType(@Param("userId") Long userId, @Param("type") Category.CategoryType type);

    //check if category name exists for user
    @Query("SELECT COUNT(c) > 0 FROM Category c WHERE c.user.id = :userId AND c.name = :name AND c.isActive = true")
    boolean categoryExistsByUserIdAndName(@Param("userId") Long userId, @Param("name") String name);
}


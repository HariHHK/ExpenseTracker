package com.hhk.expensetreackerapi.repository;

import com.hhk.expensetreackerapi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/***
 * JPA Repository for category entity
 * @author: Hari Hara Krishnan
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    /*
     * Finder method for retrieving the categories by user id
     * @param userId
     * @return list of categories
     */
    List<CategoryEntity> findByUserId(Long userId);

    /*
     * Finder method for fetching the category by user id and category id
     * @param id,categoryId
     * @return Optional<CategoryEntity>
     */
    Optional<CategoryEntity> findByUserIdAndCategoryId(Long id, String categoryId);

    /*
     * It checks whether the category exists or not by user id and category name
     * @param name,userId
     * @return boolean
     */
    boolean existsByNameAndUserId(String name, Long userId);

    /*
     * Finder method for retrieving the categories by user id and name
     * @param userId,name
     * @return Optional<CategoryEntity>
     */
    Optional<CategoryEntity> findByNameAndUserId(String name, Long userId);
}

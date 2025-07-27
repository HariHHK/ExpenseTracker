package com.hhk.expensetreackerapi.service;

import com.hhk.expensetreackerapi.dto.CategoryDTO;

import java.util.List;

/***
 * Category Service Interface for managing the categories
 * @author: Hari Hara Krishnan
 */
public interface ICategoryService {

    /***
     * This is for reading the categories from database
     * @return list of categories
     */
    List<CategoryDTO> getAllCategories();

    /***
     * This is for creating the new category
     * @param categoryDTO
     * @return CategoryDTO
     */
    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    /***
     * This is for deleting the category
     * @param categoryId
     */
    void deleteCategory(String categoryId);
}

package com.hhk.expensetreackerapi.service.impl;

import com.hhk.expensetreackerapi.dto.CategoryDTO;
import com.hhk.expensetreackerapi.dto.UserDTO;
import com.hhk.expensetreackerapi.entity.CategoryEntity;
import com.hhk.expensetreackerapi.entity.User;
import com.hhk.expensetreackerapi.exceptions.ItemExistsException;
import com.hhk.expensetreackerapi.exceptions.ResourceNotFoundException;
import com.hhk.expensetreackerapi.repository.CategoryRepository;
import com.hhk.expensetreackerapi.service.ICategoryService;
import com.hhk.expensetreackerapi.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/***
 * Category Service Implementation for managing the categories
 * @author: Hari Hara Krishnan
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final IUserService userService;

    /***
     * This is for reading the categories from database
     * @return list of categories
     */
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryEntity> list = categoryRepository.findByUserId(userService.getLoggedInUser().getId());
        return list.stream().map(categoryEntity -> mapToDTO(categoryEntity)).collect(Collectors.toList());

    }

    /***
     * This is for creating the new category
     * @param categoryDTO
     * @return CategoryDTO
     */
    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        boolean isCategoryPresent = categoryRepository.existsByNameAndUserId(categoryDTO.getName(), userService.getLoggedInUser().getId());
        if (isCategoryPresent) {
            throw new ItemExistsException("Category already exists for the name " + categoryDTO.getName());
        }
        CategoryEntity newCategory = mapToEntity(categoryDTO);
        newCategory = categoryRepository.save(newCategory);
        return mapToDTO(newCategory);
    }

    /***
     * This is for deleting the category
     * @param categoryId
     */
    @Override
    public void deleteCategory(String categoryId) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findByUserIdAndCategoryId(userService.getLoggedInUser().getId(), categoryId);
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Category is not found for the id " + categoryId);
        }
        categoryRepository.delete(optionalCategory.get());
    }

    /***
     * Mapper method for converting a CategoryDTO object to CategoryEntity object
     * @param categoryDTO
     * @return CategoryEntity
     */
    private CategoryEntity mapToEntity(CategoryDTO categoryDTO) {
        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .categoryIcon(categoryDTO.getCategoryIcon())
                .categoryId(UUID.randomUUID().toString())
                .user(userService.getLoggedInUser())
                .build();
    }

    /***
     * Mapper method for converting a CategoryEntity object to CategoryDTO object
     * @param categoryEntity
     * @return CategoryDTO
     */
    private CategoryDTO mapToDTO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .name(categoryEntity.getName())
                .categoryId(categoryEntity.getCategoryId())
                .description(categoryEntity.getDescription())
                .categoryIcon(categoryEntity.getCategoryIcon())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .user(mapToUserDTO(categoryEntity.getUser()))
                .build();
    }

    /***
     * Mapper method for converting a User object to UserDTO object
     * @param user
     * @return UserDTO
     */
    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}

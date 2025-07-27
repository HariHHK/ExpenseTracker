package com.hhk.expensetreackerapi.controller;

import com.hhk.expensetreackerapi.dto.CategoryDTO;
import com.hhk.expensetreackerapi.io.CategoryRequest;
import com.hhk.expensetreackerapi.io.CategoryResponse;
import com.hhk.expensetreackerapi.mappers.CategoryMapper;
import com.hhk.expensetreackerapi.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/***
 * This controller is for managing the categories
 * @author: Hari Hara Krishnan
 */
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    private final CategoryMapper categoryMapper;

    /*
     * API for creating a category
     * @Param categoryRequest
     * @Return CategoryResponse
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryDTO categoryDTO = categoryMapper.mapToCategoryDTO(categoryRequest);
        categoryDTO = categoryService.saveCategory(categoryDTO);
        return categoryMapper.mapToCategoryResponse(categoryDTO);
    }

    /*
     * API for reading a category
     * @Return List<CategoryResponse>
     */
    @GetMapping
    public List<CategoryResponse> readCategories() {
        List<CategoryDTO> listOfCategories = categoryService.getAllCategories();
        return listOfCategories.stream().map(category -> categoryMapper.mapToCategoryResponse(category)).collect(Collectors.toList());
    }

    /*
     * API for deleting a category
     * @Param categoryId
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
    }



    /*
     * Mapper method for converting a CategoryDTO object to CategoryResponse object
     * @Param categoryDTO
     * @Return CategoryResponse
     *
     */
//    private CategoryResponse mapToResponse(CategoryDTO categoryDTO) {
//        return CategoryResponse.builder()
//                .categoryId(categoryDTO.getCategoryId())
//                .name(categoryDTO.getName())
//                .description(categoryDTO.getDescription())
//                .categoryIcon(categoryDTO.getCategoryIcon())
//                .createdAt(categoryDTO.getCreatedAt())
//                .updatedAt(categoryDTO.getUpdatedAt())
//                .build();
//    }

    /*
     * Mapper method for converting a CategoryRequest object to CategoryDTO object
     * @Param categoryRequest
     * @Return CategoryDTO
     *
     */
//    private CategoryDTO mapToDTO(CategoryRequest categoryRequest) {
//        return CategoryDTO.builder()
//                .name(categoryRequest.getName())
//                .description(categoryRequest.getDescription())
//                .categoryIcon(categoryRequest.getIcon())
//                .build();
//    }


}

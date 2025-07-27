package com.hhk.expensetreackerapi.mappers;

import com.hhk.expensetreackerapi.dto.CategoryDTO;
import com.hhk.expensetreackerapi.entity.CategoryEntity;
import com.hhk.expensetreackerapi.io.CategoryRequest;
import com.hhk.expensetreackerapi.io.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);


    CategoryEntity mapToCategoryEntity(CategoryDTO categoryDTO);

    CategoryDTO mapToCategoryDTO(CategoryEntity entity);

    @Mapping(target = "categoryIcon", source = "icon")
    CategoryDTO mapToCategoryDTO(CategoryRequest request);

    //   @Mapping(target = "category", source = "expenseDTO.categoryDTO")
    CategoryResponse mapToCategoryResponse(CategoryDTO categoryDTO);
}

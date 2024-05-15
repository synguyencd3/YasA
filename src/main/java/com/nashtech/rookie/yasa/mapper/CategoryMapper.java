package com.nashtech.rookie.yasa.mapper;

import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.dto.response.CategoryDto;
import com.nashtech.rookie.yasa.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper { CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto toDto(Category entity);
    Category toEntity(CreateCategoryDto dto);
    Category updateEntity(@MappingTarget Category entity, UpdateCategoryDto dto);
}

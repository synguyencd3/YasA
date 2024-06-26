package com.nashtech.rookie.yasa.mapper;

import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateProductDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDto toDto(Product entity);

    @Mapping(target = "category", qualifiedByName = "mapCategory")
    Product toEntity(CreateProductDto dto);
    Product updateEntity(@MappingTarget Product entity, UpdateProductDto dto);

    @Named("mapCategory")
    default Category mapCategory(int categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }
}

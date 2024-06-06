package com.nashtech.rookie.yasa.mapper;

import com.nashtech.rookie.yasa.dto.request.CreateRatingDto;
import com.nashtech.rookie.yasa.dto.request.UpdateRatingDto;
import com.nashtech.rookie.yasa.dto.response.RatingDto;
import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RatingMapper {
    
    RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    RatingDto toDto(Rating entity);
    @Mapping(source = "product", target = "product", qualifiedByName = "mapProduct")
    Rating toEntity(CreateRatingDto dto);
    Rating updateEntity(@MappingTarget Rating entity, UpdateRatingDto dto);

    @Named("mapProduct")
    default Product mapProduct(int productId) {
        Product product = new Product();
        product.setId(productId);
        return product;
    }
}

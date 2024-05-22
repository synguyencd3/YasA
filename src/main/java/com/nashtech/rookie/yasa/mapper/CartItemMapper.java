package com.nashtech.rookie.yasa.mapper;

import com.nashtech.rookie.yasa.dto.request.CartItemDto;
import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.entity.CartDetail;
import com.nashtech.rookie.yasa.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDto toDto(CartDetail entity);
    CartDetail toEntity(CartItemDto dto);
    CartDetail updateEntity(@MappingTarget CartDetail entity, CartItemDto dto);
}
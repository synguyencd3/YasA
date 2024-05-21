package com.nashtech.rookie.yasa.mapper;

import com.nashtech.rookie.yasa.dto.request.CartItemDto;
import com.nashtech.rookie.yasa.dto.response.CartDetailDto;
import com.nashtech.rookie.yasa.entity.CartDetail;
import com.nashtech.rookie.yasa.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartDetailMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);
    @Mapping(source = "entity.product.id", target = "productId")
    CartDetailDto toDto (CartDetail entity);
    CartDetail toEntity(CartDetailDto dto);
    CartDetail updateEntity(@MappingTarget CartDetail entity, CartDetailDto dto);
}

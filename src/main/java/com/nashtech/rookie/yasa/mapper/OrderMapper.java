package com.nashtech.rookie.yasa.mapper;
import com.nashtech.rookie.yasa.dto.response.OrderDto;
import com.nashtech.rookie.yasa.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDto toDto(Order entity);
    Order toEntity(OrderDto dto);
}

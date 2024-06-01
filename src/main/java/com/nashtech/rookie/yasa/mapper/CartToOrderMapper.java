package com.nashtech.rookie.yasa.mapper;

import com.nashtech.rookie.yasa.entity.CartDetail;
import com.nashtech.rookie.yasa.entity.Order;
import com.nashtech.rookie.yasa.entity.OrderDetail;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartToOrderMapper {

    CartToOrderMapper INSTANCE = Mappers.getMapper(CartToOrderMapper.class);

    @Mappings({
            @Mapping(source = "id.productId", target = "id.productId"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(target = "id.orderId", ignore = true),
            @Mapping(target = "order", ignore = true)
    })
    OrderDetail cartDetailToOrderDetail(CartDetail cartDetail);

    //@AfterMapping
    default void setOrderIdAndOrder(@MappingTarget OrderDetail orderDetail, CartDetail cartDetail, @Context int orderId, @Context Order order) {
        orderDetail.getId().setOrderId(orderId);
        orderDetail.setOrder(order);
    }

    default OrderDetail convert(CartDetail cartDetail, int orderId, Order order) {
        OrderDetail orderDetail = cartDetailToOrderDetail(cartDetail);
        setOrderIdAndOrder(orderDetail, cartDetail, orderId, order);
        return orderDetail;
    }
}

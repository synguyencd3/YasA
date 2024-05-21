package com.nashtech.rookie.yasa.service.order;

import com.nashtech.rookie.yasa.dto.request.CreateOrderDto;
import com.nashtech.rookie.yasa.dto.response.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public OrderDto createOrder(int cartId, CreateOrderDto dto);

    public List<OrderDto> getAll();
}

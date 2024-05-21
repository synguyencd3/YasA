package com.nashtech.rookie.yasa.service.order;

import com.nashtech.rookie.yasa.dto.response.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public OrderDto createOrder(int cartId);
}

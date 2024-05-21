package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.response.OrderDto;
import com.nashtech.rookie.yasa.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/{id}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("id") int cartId){
        return ResponseEntity.ok(orderService.createOrder(cartId));
    }
}

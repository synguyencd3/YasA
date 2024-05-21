package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateOrderDto;
import com.nashtech.rookie.yasa.dto.response.OrderDto;
import com.nashtech.rookie.yasa.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/{id}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("id") int cartId, @RequestBody CreateOrderDto dto){
        return ResponseEntity.ok(orderService.createOrder(cartId, dto));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }
}

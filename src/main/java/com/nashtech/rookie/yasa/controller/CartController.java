package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CartItemDto;
import com.nashtech.rookie.yasa.dto.response.CartDto;
import com.nashtech.rookie.yasa.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable int cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable int cartId, @RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.ok(cartService.addToCart(cartId, cartItemDto));
    }
}

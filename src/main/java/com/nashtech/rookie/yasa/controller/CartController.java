package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CartItemDto;
import com.nashtech.rookie.yasa.dto.request.CartUpdateQuantityDto;
import com.nashtech.rookie.yasa.dto.response.CartDto;
import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.service.cart.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable int cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @GetMapping()
    public ResponseEntity<CartDto> getCart(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(cartService.getCart(bearerToken));
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable int cartId, @RequestBody @Valid CartItemDto cartItemDto) {
        return ResponseEntity.ok(cartService.addToCart(cartId, cartItemDto));
    }

    @PostMapping()
    public ResponseEntity<CartDto> addProductToCart(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid CartItemDto cartItemDto) {
        return ResponseEntity.ok(cartService.addToCart(bearerToken, cartItemDto));
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<CartDto> removeProductFromCart(@PathVariable int cartId,@PathVariable int productId) {
        return ResponseEntity.ok(cartService.removeProductFromCart(cartId, productId));
    }

    @PatchMapping("/{cartId}/products/{productId}")
    public ResponseEntity<CartDto> updateProductQuantity(@PathVariable int cartId, @PathVariable int productId, @RequestBody @Valid CartUpdateQuantityDto dto) {
        return ResponseEntity.ok(cartService.updateProductQuantity(cartId,productId,dto));
    }
}

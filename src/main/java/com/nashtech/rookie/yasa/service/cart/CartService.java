package com.nashtech.rookie.yasa.service.cart;
import com.nashtech.rookie.yasa.dto.request.CartItemDto;
import com.nashtech.rookie.yasa.dto.response.CartDto;
import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart createCart(User user);

    CartDto getCart(int id);

    CartDto addToCart(int cartId, CartItemDto cartItem);
}

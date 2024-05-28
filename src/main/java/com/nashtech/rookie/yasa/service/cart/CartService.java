package com.nashtech.rookie.yasa.service.cart;
import com.nashtech.rookie.yasa.dto.request.CartItemDto;
import com.nashtech.rookie.yasa.dto.request.CartUpdateQuantityDto;
import com.nashtech.rookie.yasa.dto.response.CartDto;
import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Cart createCart(User user);

    CartDto getCart(int id);

    CartDto getCart(String bearerToken);

    CartDto addToCart(int cartId, CartItemDto cartItem);

    CartDto addToCart(String bearerToken, CartItemDto cartItem);

    CartDto removeProductFromCart(int cartId, int productId);

    CartDto updateProductQuantity(int cartId, int productId, CartUpdateQuantityDto dto);
}

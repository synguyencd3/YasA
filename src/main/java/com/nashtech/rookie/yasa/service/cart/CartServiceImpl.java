package com.nashtech.rookie.yasa.service.cart;

import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.entity.User;
import com.nashtech.rookie.yasa.mapper.CategoryMapper;
import com.nashtech.rookie.yasa.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart createCart(User user) {
       Cart cart = new Cart();
       cart.setUser(user);
       return cartRepository.save(cart);
    }


}

package com.nashtech.rookie.yasa.service.cart;

import com.nashtech.rookie.yasa.dto.request.CartItemDto;
import com.nashtech.rookie.yasa.dto.request.CartUpdateQuantityDto;
import com.nashtech.rookie.yasa.dto.response.CartDto;
import com.nashtech.rookie.yasa.entity.*;
import com.nashtech.rookie.yasa.exceptions.NotFoundException;
import com.nashtech.rookie.yasa.mapper.CartItemMapper;
import com.nashtech.rookie.yasa.mapper.CartMapper;
import com.nashtech.rookie.yasa.repository.CartDetailRepository;
import com.nashtech.rookie.yasa.repository.CartRepository;
import com.nashtech.rookie.yasa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart createCart(User user) {
       Cart cart = new Cart();
       cart.setUser(user);
       return cartRepository.save(cart);
    }

    @Override
    public CartDto getCart(int id){
        return cartRepository.findById(id).map(CartMapper.INSTANCE::toDto).orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    @Override
    public CartDto addToCart(int cartId, CartItemDto cartItem) {

        CartDetail cartDetail = CartItemMapper.INSTANCE.toEntity(cartItem);
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));
        Product product = productRepository.findById(cartItem.getProductId() ).orElseThrow(() -> new NotFoundException("Product not found"));

        //init key
        CartDetailKey key = new CartDetailKey();
        key.setCartId(cart.getId());
        key.setProductId(product.getId());


        Optional<CartDetail> isExisted = cartDetailRepository.findById(key);
        if (isExisted.isEmpty())
        {
            cartDetail.setId(key);
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cart.getProducts().add(cartDetail);
            cartDetailRepository.save(cartDetail);
        } else
        {
            CartDetail existedCart = isExisted.get();
            //existedCart.addQuantity(cartItem.getQuantity());
            addQuantity(existedCart, cartItem.getQuantity());
            cart.getProducts().add(existedCart);
            cartDetailRepository.save(existedCart);
        }
        return CartMapper.INSTANCE.toDto(cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found")));
    }

    private void addQuantity(CartDetail target, int quantity) {
        target.setQuantity(target.getQuantity()+quantity);
    }

    @Override
    public CartDto removeProductFromCart(int cartId, int productId) {
        CartDetailKey key = new CartDetailKey();
        key.setProductId(productId);
        key.setCartId(cartId);
        cartDetailRepository.deleteById(key);
        return getCart(cartId);
    }

    @Override
    public CartDto updateProductQuantity(int cartId, int productId, CartUpdateQuantityDto dto) {
        CartDetailKey key = new CartDetailKey();
        key.setProductId(productId);
        key.setCartId(cartId);

        CartDetail cartDetail = cartDetailRepository.findById(key).orElseThrow(() -> new NotFoundException("Cart or Product not found"));
        cartDetail.setQuantity(dto.getQuantity());
        cartDetailRepository.save(cartDetail);

        return getCart(cartId);
    }


}

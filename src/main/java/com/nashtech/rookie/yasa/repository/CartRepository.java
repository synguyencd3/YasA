package com.nashtech.rookie.yasa.repository;
import com.nashtech.rookie.yasa.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}

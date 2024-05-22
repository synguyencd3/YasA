package com.nashtech.rookie.yasa.repository;

import com.nashtech.rookie.yasa.entity.CartDetail;
import com.nashtech.rookie.yasa.entity.CartDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailKey> {
    List<CartDetail> findByCartId(int cartId);

    void deleteByCartId(int cartId);
}

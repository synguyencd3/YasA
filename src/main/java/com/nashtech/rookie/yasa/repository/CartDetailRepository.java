package com.nashtech.rookie.yasa.repository;

import com.nashtech.rookie.yasa.entity.CartDetail;
import com.nashtech.rookie.yasa.entity.CartDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, CartDetailKey> {
}

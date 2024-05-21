package com.nashtech.rookie.yasa.repository;

import com.nashtech.rookie.yasa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}

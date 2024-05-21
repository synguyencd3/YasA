package com.nashtech.rookie.yasa.repository;
import com.nashtech.rookie.yasa.entity.OrderDetail;
import com.nashtech.rookie.yasa.entity.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
}

package com.nashtech.rookie.yasa.repository;

import com.nashtech.rookie.yasa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByCategory_Id(int categoryid, Pageable pageable);
}

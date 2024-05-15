package com.nashtech.rookie.yasa.repository;

import com.nashtech.rookie.yasa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_Id(int categoryid);
}
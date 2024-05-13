package com.nashtech.rookie.yasa.service.product;

import com.nashtech.rookie.yasa.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product createProduct(Product newProduct);
}

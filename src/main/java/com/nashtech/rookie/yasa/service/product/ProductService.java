package com.nashtech.rookie.yasa.service.product;

import com.nashtech.rookie.yasa.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product createProduct(Product newProduct);

    public Optional<Product> getProduct(int id);

    public List<Product> getAllInCategory(int id);
}

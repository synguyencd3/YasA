package com.nashtech.rookie.yasa.service.product;

import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().toList();
    }

    @Override
    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    @Override
    public Optional<Product> getProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllInCategory(int id) {
        return productRepository.findByCategory_Id(id);
    }


}

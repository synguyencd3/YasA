package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping(value = {"","/"})
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product newProduct) {
       return productService.createProduct(newProduct);
    }
}

package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping()
    public List<Product> getAll(@RequestParam(required = false, name="category") Optional<Integer> category) {
        if (category.isEmpty())
            return productService.getAllProducts();
        else
            return productService.getAllInCategory(category.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product newProduct) {
       return productService.createProduct(newProduct);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<Product> getProduct(@PathVariable("id") int id) {
        return productService.getProduct(id);
    }


}

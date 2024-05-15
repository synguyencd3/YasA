package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateProductDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll(@RequestParam(required = false, name="category") Optional<Integer> category) {
        return category.map(integer -> ResponseEntity.ok(productService.getAllInCategory(integer))).orElseGet(() -> ResponseEntity.ok(productService.getAllProducts()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductDto newProduct) {
       return ResponseEntity.ok(productService.createProduct(newProduct));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") int id) {

        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") int id,@RequestBody UpdateProductDto product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

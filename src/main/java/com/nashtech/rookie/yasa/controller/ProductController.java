package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateProductDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.service.product.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping()
    @CrossOrigin
    public ResponseEntity<Page<ProductDto>> getAll(
            @RequestParam(required = false, name="category") Integer category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        if (category!= null)
            return ResponseEntity.ok(productService.getAllInCategory(category, page, size));
        return ResponseEntity.ok(productService.getAllProducts(page,size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductDto newProduct) {
       return ResponseEntity.ok(productService.createProduct(newProduct));
    }


    @GetMapping("/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") @Min(value = 1, message = "invalid product id") int id) {

        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id")  @Min(value = 1, message = "invalid product id")int id,@RequestBody @Valid UpdateProductDto product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id")  @Min(value = 1, message = "invalid product id") int id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

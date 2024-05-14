package com.nashtech.rookie.yasa.service.product;

import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateProductDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.entity.Product;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto createProduct(CreateProductDto dto);

    //public ProductDto createProduct(Product dto);

    public ProductDto getProduct(int id);

    public List<ProductDto> getAllInCategory(int id);

    public ProductDto updateProduct(int id, UpdateProductDto dto);
}

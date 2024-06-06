package com.nashtech.rookie.yasa.service.product;

import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateProductDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Page<ProductDto> getAllProducts(int page, int size, String sort, String sortBy);
    public ProductDto createProduct(CreateProductDto dto);

    public ProductDto getProduct(int id);

    public Page<ProductDto> getAllInCategory(int id, int page, int size, String sort, String sortBy);

    public ProductDto updateProduct(int id, UpdateProductDto dto);

    public void deleteProduct(int id);
}

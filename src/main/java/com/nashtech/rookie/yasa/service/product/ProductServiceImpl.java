package com.nashtech.rookie.yasa.service.product;

import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateProductDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.exceptions.NotFoundException;
import com.nashtech.rookie.yasa.mapper.ProductMapper;
import com.nashtech.rookie.yasa.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductDto> getAllProducts(int page, int size, String sort, String sortBy) {
        Sort.Direction sortDirection = Sort.Direction.fromString(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection,sortBy));
        return productRepository.findAll(pageable).map(ProductMapper.INSTANCE::toDto);
    }

    @Override
    public ProductDto createProduct(CreateProductDto dto) {
        Product product = ProductMapper.INSTANCE.toEntity(dto);//mapper.toEntity(dto);
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.toDto(product);
    }

    @Override
    public ProductDto getProduct(int id) {
        return productRepository.findById(id).map(ProductMapper.INSTANCE::toDto).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Page<ProductDto> getAllInCategory(int id, int page, int size, String sort, String sortBy) {
        Sort.Direction sortDirection = Sort.Direction.fromString(sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection,sortBy));
        return productRepository.findByCategory_Id(id, pageable).map(ProductMapper.INSTANCE::toDto);
    }


    @Override
    @Transactional
    public ProductDto updateProduct(int id, UpdateProductDto dto) {
        return productRepository.findById(id).map(product -> {
            var updatedProduct = ProductMapper.INSTANCE.updateEntity(product,dto);
            updatedProduct = productRepository.save(updatedProduct);
            return ProductMapper.INSTANCE.toDto(updatedProduct);
        }).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }


}

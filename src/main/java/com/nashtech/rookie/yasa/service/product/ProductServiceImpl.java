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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
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
    public List<ProductDto> getAllInCategory(int id) {
        return productRepository.findByCategory_Id(id).stream().map(ProductMapper.INSTANCE::toDto).collect(Collectors.toList());
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

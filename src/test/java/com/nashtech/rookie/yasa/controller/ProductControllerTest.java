package com.nashtech.rookie.yasa.controller;


import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.repository.ProductRepository;
import com.nashtech.rookie.yasa.service.product.ProductService;
import com.nashtech.rookie.yasa.service.product.ProductServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;
    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ProductDto getSampleProduct() {
        ProductDto sampleProduct = new ProductDto();
        sampleProduct.setId(1);
        sampleProduct.setName("test");
        return sampleProduct;
    }

    private Page<ProductDto> getSamplePage() {
        ProductDto sampleProduct1 = new ProductDto();
        sampleProduct1.setId(1);
        sampleProduct1.setName("test");
        ProductDto sampleProduct2 = new ProductDto();
        sampleProduct2.setId(2);
        sampleProduct2.setName("test");
        

        var productList = List.of(sampleProduct1,sampleProduct2);
        Sort.Direction sortDirection = Sort.Direction.fromString("asc");
        PageRequest pageRequest = PageRequest.of(0, 2,Sort.by(sortDirection,"id"));
        return new PageImpl<>(productList, pageRequest, productList.size());
    }

    @Test
    public void testGetProductById() {
        var sampleProduct = getSampleProduct();
        when(productService.getProduct(1)).thenReturn(sampleProduct);
        ResponseEntity<ProductDto> actualProduct = productController.getProduct(1);
        assertNotNull(actualProduct.getBody());
    }

    @Test
    public void testGetAllProduct() {
        var samplePage = getSamplePage();
        when(productService.getAllProducts(0,2,"asc","id")).thenReturn(samplePage);
        var actualPage = productController.getAll(null,0,2,"asc","id");

        assertThat(actualPage.getBody()).isEqualTo(samplePage);
    }

}
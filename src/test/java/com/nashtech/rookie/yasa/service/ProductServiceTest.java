package com.nashtech.rookie.yasa.service;
import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateProductDto;
import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.mapper.ProductMapper;
import com.nashtech.rookie.yasa.repository.ProductRepository;
import com.nashtech.rookie.yasa.service.product.ProductServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void shouldReturnAllProduct()
    {
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("test1");

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("test2");

        given(productRepository.findAll()).willReturn(List.of(product1,product2));
        var productList = productService.getAllProducts();

        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);
    }

    @BeforeEach
    public void reset_mock() {
        productService = mock(ProductServiceImpl.class);
    }

    @Test
    public void addProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(new Product());
        CreateProductDto product = new CreateProductDto();
        assertNotNull(productService.createProduct(product));
    }

   @Test
    public void whenGivenId_deleteProduct() {
        Product product = new Product();
        product.setName("test");
        product.setId(1);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.deleteProduct(product.getId());
        verify(productRepository).deleteById(product.getId());
   }

   @Test
   public void should_throw_exception_when_product_doesnt_exist() {
        Product product = new Product();
        product.setId(1);
        product.setName("test");
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        productService.deleteProduct(product.getId());
   }

    @Test
    public void whenGivenId_shouldUpdateProduct_ifFound() {

        //init product
        Product product = new Product();
        product.setId(1);
        product.setName("test");

        //init a updated product
        UpdateProductDto newProduct = new UpdateProductDto();
        newProduct.setName("new test name");
        var updatedProduct = ProductMapper.INSTANCE.updateEntity(product, newProduct);

        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.updateProduct(product.getId(), newProduct);

        verify(productRepository).save(updatedProduct);
        verify(productRepository).findById(product.getId());
    }

    @Test
    public void whenGivenId_FindProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("test");

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        var Test = productService.getProduct(1);

        assertNotNull(Test);
        assertThat(Test.getId()).isEqualTo(product.getId());
    }

}

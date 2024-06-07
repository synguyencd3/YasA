package com.nashtech.rookie.yasa.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.rookie.yasa.dto.request.CreateProductDto;
import com.nashtech.rookie.yasa.dto.request.UpdateProductDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.security.JwtTokenFilter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class ProductControllerTest {

    @MockBean
    private ProductServiceImpl productService;

    @MockBean
    private JwtTokenFilter filter;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private ProductDto getSampleProduct() {
        ProductDto sampleProduct = new ProductDto();
        sampleProduct.setId(1);
        sampleProduct.setName("test");
        return sampleProduct;
    }

    private Page<ProductDto> getSamplePage() {
        ProductDto sampleProduct1 = new ProductDto();
        Category category = new Category();
        category.setId(3);
        sampleProduct1.setId(1);
        sampleProduct1.setName("test");
        sampleProduct1.setCategory(category);
        ProductDto sampleProduct2 = new ProductDto();
        sampleProduct2.setId(2);
        sampleProduct2.setName("test");
        sampleProduct2.setCategory(category);


        var productList = List.of(sampleProduct1,sampleProduct2);
        Sort.Direction sortDirection = Sort.Direction.fromString("asc");
        PageRequest pageRequest = PageRequest.of(0, 2,Sort.by(sortDirection,"id"));
        return new PageImpl<>(productList, pageRequest, productList.size());
    }

    @Test
    @WithMockUser
    public void testGetProductById() throws Exception {
        var sampleProduct = getSampleProduct();
        when(productService.getProduct(1)).thenReturn(sampleProduct);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/1")).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @WithMockUser
    public void testGetAllProduct() throws Exception {
        var samplePage = getSamplePage();
        when(productService.getAllProducts(0,2,"asc","id")).thenReturn(samplePage);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/?page=0&size=2&sort=asc&sortBy=id")).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @WithMockUser
    public void testGetAllInCategory() throws Exception {
        var samplePage = getSamplePage();
        when(productService.getAllInCategory(3,0,2,"asc","id")).thenReturn(samplePage);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/?category=1&page=0&size=2&sort=asc&sortBy=id"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @WithMockUser(username = "synguyencd", roles = "admin")
    public void testAddProduct() throws Exception {
        var sampleProduct = getSampleProduct();
        var dto = new CreateProductDto();
        dto.setName("test");
        when(productService.createProduct(dto)).thenReturn(sampleProduct);

        String inputJson = mapper.writeValueAsString(sampleProduct);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/")
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION,"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VycyBEZXRhaWxzIiwicm9sZSI6ImFkbWluIiwiY2FydElkIjoxLCJpc3MiOiJLdXJnZXJCaW5nIiwibmFtZSI6Im5ndXllbiIsImV4cCI6MTcxODA0MjM1NywidXNlcklkIjoxLCJpYXQiOjE3MTc2ODIzNTcsImp0aSI6Ijg2MmE5ZTBmLTBmMGItNGEzZi04NTlkLTlkMTdlYjE4YWJmMSIsInVzZXJuYW1lIjoic3luZ3V5ZW5jZCIsInN0YXR1cyI6ImFjdGl2ZSJ9.wLwUW7cL6qTFdj8uo29sKi3qQ5076NmR-n1Nur9yb_M")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }
}
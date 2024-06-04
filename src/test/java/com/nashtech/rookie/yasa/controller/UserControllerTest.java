//package com.nashtech.rookie.yasa.controller;
//
//import com.nashtech.rookie.yasa.entity.Product;
//import com.nashtech.rookie.yasa.service.product.ProductService;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = ProductController.class)
//@ActiveProfiles("test")
//public class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProductService productService;
//
//    private List<Product> productList;
//
//    public Product newProduct(String name, int id) {
//        Product product = new Product();
//        product.setName(name);
//        product.setId(id);
//        return product;
//    }
//
//    @BeforeEach
//    void setUp() {
//        this.productList = new ArrayList<>();
//        this.productList.add(newProduct("test",1));
//        this.productList.add(newProduct("test2",2));
//        this.productList.add(newProduct("test3",3));
//    }
//
////    @Test
////    public void shouldFetchAllProduct() {
////        given(productService.getAllProducts(0,99)).willReturn(any(Page.class));
////
////        this.mockMvc.perform(get("api/users"))
////                .andExpect(status().isOk())
////                .and
////    }
//}

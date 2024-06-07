package com.nashtech.rookie.yasa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.dto.response.CategoryDto;
import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.repository.CategoryRepository;
import com.nashtech.rookie.yasa.security.JwtTokenFilter;
import com.nashtech.rookie.yasa.service.category.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers = CategoryController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private JwtTokenFilter filter;

    @Autowired
    private MockMvc mockMvc;


    private CategoryDto getSampleCategory() {
        CategoryDto category = new CategoryDto();
        category.setId(1);
        category.setName("test");
        return category;
    }

    private Page<CategoryDto> getSamplePageDto() {
        CategoryDto sample1 = new CategoryDto();
        sample1.setName("test1");
        sample1.setId(1);
        CategoryDto sample2 = new CategoryDto();
        sample2.setName("test2");
        sample2.setId(2);

        var list = List.of(sample1,sample2);
        Sort.Direction sortDirection = Sort.Direction.fromString("asc");
        PageRequest pageRequest = PageRequest.of(0, 2,Sort.by(sortDirection,"id"));
        return new PageImpl<>(list, pageRequest, list.size());

    }

    private Page<Category> getSamplePage() {
        Category sample1 = new Category();
        sample1.setName("test1");
        sample1.setId(1);
        Category sample2 = new Category();
        sample2.setName("test2");
        sample2.setId(2);

        var list = List.of(sample1,sample2);
        Sort.Direction sortDirection = Sort.Direction.fromString("asc");
        PageRequest pageRequest = PageRequest.of(0, 2,Sort.by(sortDirection,"id"));
        return new PageImpl<>(list, pageRequest, list.size());

    }

    @Test
    @WithMockUser
    public void getAllCategory() throws Exception {
        var pageDto = getSamplePageDto();
        when(this.categoryService.getAll(0,2,"asc","id")).thenReturn(pageDto);
        MvcResult mvcResult =this.mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .param("page", "0")
                        .param("size","2")
                        .param("sort","asc")
                        .param("orderBy","id"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @WithMockUser
    public void getCategory() throws Exception {
        var sample = getSampleCategory();
        when(categoryService.getCategory(1)).thenReturn(sample);
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/1")).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @WithMockUser(username = "synguyencd", roles = "admin")
    public void createCategory() throws Exception {
        var sample = getSampleCategory();
        var dto = new CreateCategoryDto();
        dto.setName("test");
        when(categoryService.createCategory(dto)).thenReturn(sample);

        String inputJson = mapper.writeValueAsString(sample);
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("")
                .with(csrf())
                .header(HttpHeaders.AUTHORIZATION,"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VycyBEZXRhaWxzIiwicm9sZSI6ImFkbWluIiwiY2FydElkIjoxLCJpc3MiOiJLdXJnZXJCaW5nIiwibmFtZSI6Im5ndXllbiIsImV4cCI6MTcxODA0MjM1NywidXNlcklkIjoxLCJpYXQiOjE3MTc2ODIzNTcsImp0aSI6Ijg2MmE5ZTBmLTBmMGItNGEzZi04NTlkLTlkMTdlYjE4YWJmMSIsInVzZXJuYW1lIjoic3luZ3V5ZW5jZCIsInN0YXR1cyI6ImFjdGl2ZSJ9.wLwUW7cL6qTFdj8uo29sKi3qQ5076NmR-n1Nur9yb_M")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andReturn();


        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @WithMockUser(username = "synguyencd", roles = "admin")
    public void updateCategory() throws Exception {
        var sample = getSampleCategory();
        var dto  = new UpdateCategoryDto();
        dto.setName("test");
        when(categoryService.updateCategory(1,dto)).thenReturn(sample);

        String inputJson = mapper.writeValueAsString(sample);
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.put("/1")
                        .with(csrf())
                        .header(HttpHeaders.AUTHORIZATION,"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VycyBEZXRhaWxzIiwicm9sZSI6ImFkbWluIiwiY2FydElkIjoxLCJpc3MiOiJLdXJnZXJCaW5nIiwibmFtZSI6Im5ndXllbiIsImV4cCI6MTcxODA0MjM1NywidXNlcklkIjoxLCJpYXQiOjE3MTc2ODIzNTcsImp0aSI6Ijg2MmE5ZTBmLTBmMGItNGEzZi04NTlkLTlkMTdlYjE4YWJmMSIsInVzZXJuYW1lIjoic3luZ3V5ZW5jZCIsInN0YXR1cyI6ImFjdGl2ZSJ9.wLwUW7cL6qTFdj8uo29sKi3qQ5076NmR-n1Nur9yb_M")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }
}

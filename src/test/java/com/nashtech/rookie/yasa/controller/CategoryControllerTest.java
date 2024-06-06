package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.dto.response.CategoryDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.repository.CategoryRepository;
import com.nashtech.rookie.yasa.security.JwtTokenFilter;
import com.nashtech.rookie.yasa.service.category.CategoryServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
@WebAppConfiguration
public class CategoryControllerTest {

    @MockBean
    private CategoryServiceImpl categoryService;
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private JwtTokenFilter filter;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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
        var page= getSamplePage();
        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(categoryService.getAll(0,2,"asc","id")).thenReturn(pageDto);

        MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.get("/?page=0&size=0&sort=asc&sortBy=id"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("");

    }

    @Test
    public void getCategory() {
//        var sample = getSampleCategory();
//        when(categoryService.getCategory(1)).thenReturn(sample);
//        var actual = categoryController.getCategory(1);
//
//        assertNotNull(actual.getBody());
//        assertThat(actual.getBody()).isEqualTo(sample);
    }

    @Test
    public void createCategory() {
//        var sample = getSampleCategory();
//        var dto = new CreateCategoryDto();
//        dto.setName("test");
//        when(categoryService.createCategory(dto)).thenReturn(sample);
//        var actual = categoryController.newCategory(dto);
//
//        assertNotNull(actual.getBody());
//        assertThat(actual.getBody()).isEqualTo(sample);
    }

    @Test
    public void updateCategory() {
//        var sample = getSampleCategory();
//        var dto  = new UpdateCategoryDto();
//        dto.setName("test");
//        when(categoryService.updateCategory(1,dto)).thenReturn(sample);
//        var actual = categoryController.updateCategory(1,dto);
//
//        assertNotNull(actual.getBody());
//        assertThat(actual.getBody()).isEqualTo(sample);
    }
}

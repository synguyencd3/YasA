package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.dto.response.CategoryDto;
import com.nashtech.rookie.yasa.dto.response.ProductDto;
import com.nashtech.rookie.yasa.service.category.CategoryServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryControllerTest {

    @Mock
    private CategoryServiceImpl categoryService;
    @InjectMocks
    private CategoryController categoryController;

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

    private Page<CategoryDto> getSamplePage() {
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

    @Test
    public void getAllCategory() {
        var page = getSamplePage();
        when(categoryService.getAll(0,2,"asc","id")).thenReturn(page);
        var actual = categoryController.getAll(0,2,"asc","id");

        assertNotNull(actual.getBody());
        assertThat(actual.getBody()).isEqualTo(page);
    }

    @Test
    public void getCategory() {
        var sample = getSampleCategory();
        when(categoryService.getCategory(1)).thenReturn(sample);
        var actual = categoryController.getCategory(1);

        assertNotNull(actual.getBody());
        assertThat(actual.getBody()).isEqualTo(sample);
    }

    @Test
    public void createCategory() {
        var sample = getSampleCategory();
        var dto = new CreateCategoryDto();
        dto.setName("test");
        when(categoryService.createCategory(dto)).thenReturn(sample);
        var actual = categoryController.newCategory(dto);

        assertNotNull(actual.getBody());
        assertThat(actual.getBody()).isEqualTo(sample);
    }

    @Test
    public void updateCategory() {
        var sample = getSampleCategory();
        var dto  = new UpdateCategoryDto();
        dto.setName("test");
        when(categoryService.updateCategory(1,dto)).thenReturn(sample);
        var actual = categoryController.updateCategory(1,dto);

        assertNotNull(actual.getBody());
        assertThat(actual.getBody()).isEqualTo(sample);
    }
}

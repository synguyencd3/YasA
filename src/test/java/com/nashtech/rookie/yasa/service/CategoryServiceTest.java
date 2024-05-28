package com.nashtech.rookie.yasa.service;
import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.service.category.CategoryServiceImpl;
import com.nashtech.rookie.yasa.repository.CategoryRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void reset_mock() {
        categoryService = mock(CategoryServiceImpl.class);
    }

    @Test
    public void shouldReturnAllCategories()
    {
        Category category1= new Category();
        category1.setName("test");
        category1.setId(1);

        Category category2 = new Category();
        category2.setName("test2");
        category2.setId(1);

        given(categoryRepository.findAll()).willReturn(List.of(category1,category2));
        var categoryList = categoryService.getAll();

        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(2);
    }

    @Test
    public void addCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
        CreateCategoryDto category = new CreateCategoryDto();
        assertNotNull(categoryService.createCategory(category));
    }
}

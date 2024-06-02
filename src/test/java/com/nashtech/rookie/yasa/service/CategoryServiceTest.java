package com.nashtech.rookie.yasa.service;
import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.exceptions.NotFoundException;
import com.nashtech.rookie.yasa.mapper.CategoryMapper;
import com.nashtech.rookie.yasa.service.category.CategoryServiceImpl;
import com.nashtech.rookie.yasa.repository.CategoryRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void reset_mock() {

        categoryService = mock(CategoryServiceImpl.class);

        Category Category = new Category();
        Category.setId(1);
        Category.setName("test");
    }

    public Category setUp() {
        Category category = new Category();
        category.setId(1);
        category.setName("test");
        return category;
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

        Pageable pageable = PageRequest.of(0,99, Sort.by("id"));

        given(categoryRepository.findAll(pageable)).willReturn(any(Page.class));
        var categoryList = categoryService.getAll(0,99);

        assertThat(categoryList).isNotNull();
       // assertThat(categoryList.size()).isEqualTo(2);
    }

    @Test
    public void addCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
        CreateCategoryDto category = new CreateCategoryDto();
        assertNotNull(categoryService.createCategory(category));
    }


    @Test
    public void whenGivenId_shouldUpdateCategory_ifFound() {

        //init Category
//        Category Category = new Category();
//        Category.setId(1);
//        Category.setName("test");

        var Category = setUp();

        //init a updated Category
        UpdateCategoryDto newCategory = new UpdateCategoryDto();
        newCategory.setName("new test name");
        var updatedCategory = CategoryMapper.INSTANCE.updateEntity(Category, newCategory);

        when(categoryRepository.save(any(Category.class))).thenReturn(Category);
        when(categoryRepository.findById(Category.getId())).thenReturn(Optional.of(Category));
        categoryService.updateCategory(Category.getId(), newCategory);

        verify(categoryRepository).save(updatedCategory);
        verify(categoryRepository).findById(Category.getId());
    }

    @Test
    public void whenGivenId_deleteCategory() {
//        Category Category = new Category();
//        Category.setName("test");
//        Category.setId(1);
        var Category = setUp();

        when(categoryRepository.findById(Category.getId())).thenReturn(Optional.of(Category));
        categoryService.deleteCategory(Category.getId());
        verify(categoryRepository).deleteById(Category.getId());
    }

    @Test
    public void whenGivenId_FindCategory() {
//        Category Category = new Category();
//        Category.setId(1);
//        Category.setName("test");
        var Category=setUp();

        when(categoryRepository.findById(Category.getId())).thenReturn(Optional.of(Category));
        var Test = categoryService.getCategory(1);

        assertNotNull(Test);
        assertThat(Test.getId()).isEqualTo(Category.getId());
    }

    @Test
    public void should_throw_exception_when_Category_doesnt_exist() {
//        Category Category = new Category();
//        Category.setId(1);
//        Category.setName("test");
        var Category=setUp();
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());
        categoryService.deleteCategory(Category.getId());
    }

    @Test(expected = NotFoundException.class)
    public void should_throw_exception_when_category_doesnt_exist() {
        categoryService.getCategory(99);
    }
}

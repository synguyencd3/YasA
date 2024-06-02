package com.nashtech.rookie.yasa.service.category;

import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.dto.response.CategoryDto;
import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public Page<CategoryDto> getAll(int page, int size);

    public CategoryDto getCategory(int id);
    public CategoryDto createCategory(CreateCategoryDto dto);
    public CategoryDto updateCategory(int id,UpdateCategoryDto dto);
    public void deleteCategory(int id);
}

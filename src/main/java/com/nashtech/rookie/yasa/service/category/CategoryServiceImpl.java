package com.nashtech.rookie.yasa.service.category;

import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.dto.response.CategoryDto;
import com.nashtech.rookie.yasa.exceptions.NotFoundException;
import com.nashtech.rookie.yasa.mapper.CategoryMapper;
import com.nashtech.rookie.yasa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id"));
        return categoryRepository.findAll(pageable).stream().map(CategoryMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategory(int id) {
        return categoryRepository.findById(id).map(CategoryMapper.INSTANCE::toDto).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public CategoryDto createCategory(CreateCategoryDto dto) {
        var category = CategoryMapper.INSTANCE.toEntity(dto);
        category = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.toDto(category);
    }

    @Override
    public CategoryDto updateCategory(int id,UpdateCategoryDto dto) {
        return categoryRepository.findById(id).map(category -> {
            var updatedCategory = CategoryMapper.INSTANCE.updateEntity(category, dto);
            updatedCategory = categoryRepository.save(updatedCategory);
            return CategoryMapper.INSTANCE.toDto(updatedCategory);
        }).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

}

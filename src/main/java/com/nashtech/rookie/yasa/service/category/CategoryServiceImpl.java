package com.nashtech.rookie.yasa.service.category;

import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }
}

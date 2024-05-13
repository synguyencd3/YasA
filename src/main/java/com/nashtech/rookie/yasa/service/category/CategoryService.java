package com.nashtech.rookie.yasa.service.category;

import com.nashtech.rookie.yasa.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public List<Category> getAll();
    public Category createCategory(Category newCategory);
}

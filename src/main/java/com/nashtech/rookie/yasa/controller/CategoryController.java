package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.entity.Category;
import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.service.category.CategoryService;
import com.nashtech.rookie.yasa.service.category.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(value ={"","/"})
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Category newCategory(@RequestBody Category newCategoty) {
       return categoryService.createCategory(newCategoty);
    }


}

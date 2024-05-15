package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.dto.response.CategoryDto;
import com.nashtech.rookie.yasa.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(value ={"","/"})
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> newCategory(@RequestBody CreateCategoryDto dto) {
       return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") int id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") int id, @RequestBody UpdateCategoryDto dto ){
        return ResponseEntity.ok(categoryService.updateCategory(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


}

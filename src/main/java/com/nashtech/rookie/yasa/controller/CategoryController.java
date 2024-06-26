package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateCategoryDto;
import com.nashtech.rookie.yasa.dto.request.UpdateCategoryDto;
import com.nashtech.rookie.yasa.dto.response.CategoryDto;
import com.nashtech.rookie.yasa.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(value ={"","/"})
    @CrossOrigin
    public ResponseEntity<Page<CategoryDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(categoryService.getAll(page, size, sort, sortBy));
    }

    @PostMapping(value ={"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> newCategory(@RequestBody CreateCategoryDto dto) {
       return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") int id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") int id, @RequestBody UpdateCategoryDto dto ){
        return ResponseEntity.ok(categoryService.updateCategory(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


}

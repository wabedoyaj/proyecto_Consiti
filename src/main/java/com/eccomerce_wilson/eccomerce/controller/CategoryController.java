package com.eccomerce_wilson.eccomerce.controller;

import com.eccomerce_wilson.eccomerce.Entity.Category;
import com.eccomerce_wilson.eccomerce.Exceptions.CategoryExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.NotFound.CategoryNotFoundException;
import com.eccomerce_wilson.eccomerce.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok(service.getAllCategory());
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<Category> getCategoryId(@PathVariable Long categoryId){
        Category category = service.getCategoryById(categoryId);
        if (category == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(service.createCategory(category));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        Category updatedCategory = service.updateCategory(categoryId, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        service.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(CategoryExistsException.class)
    public ResponseEntity<String> handleCategoryExistsException(CategoryExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}

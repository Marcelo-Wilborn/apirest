package com.dev.apirest.controllers;

import com.dev.apirest.domain.Category;
import com.dev.apirest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {
    
    private final CategoryService service;
    
    public CategoryController(CategoryService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = service.findCategoryById(id);
        return ResponseEntity.ok().body(category);
    }
    
    @PostMapping
    public ResponseEntity<Category> createOrder(@RequestBody Category category){
        Category createdCategory = service.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
    
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        category = service.updateCategory(id, category);
        return ResponseEntity.ok().body(category);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
    
    
}

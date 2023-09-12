package com.dev.apirest.controllers;

import com.dev.apirest.domain.Product;
import com.dev.apirest.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")

public class ProductController {
    
        
        private final ProductService service;
    
    public ProductController(ProductService service) {
        this.service = service;
    }
    
    @GetMapping
        public ResponseEntity<List<Product>> findAll() {
            List<Product> list = service.findAll();
            return ResponseEntity.ok().body(list);
        }
        
        @GetMapping(value = "/{id}")
        public ResponseEntity<Product> findProductById(@PathVariable Long id) {
            Product product = service.findProductById(id);
            return ResponseEntity.ok().body(product);
        }
        @GetMapping(value="/findbyname/{name}")
        public List<Product> findProductByName(@PathVariable String name) {
            return service.findProductByName(name);
        }
    
        @PostMapping
        public ResponseEntity<Product> createProduct(@RequestBody Product product){
            Product createdProduct = service.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        }
    
        @PutMapping(value = "/{id}")
        public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
            product = service.updateProduct(id, product);
            return ResponseEntity.ok().body(product);
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
            service.deleteProductById(id);
            return ResponseEntity.noContent().build();
        }
    }

    
   


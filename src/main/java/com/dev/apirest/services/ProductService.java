package com.dev.apirest.services;

import com.dev.apirest.domain.Product;
import com.dev.apirest.domain.User;
import com.dev.apirest.repositories.ProductRepository;
import com.dev.apirest.repositories.UserRepository;
import com.dev.apirest.services.exceptions.DatabaseException;
import com.dev.apirest.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    
    
    private final ProductRepository repository;
    
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    
    public List<Product> findAll() {
        return repository.findAll();
    }
    
    public Product findProductById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    
    public Product createProduct(Product product) {
        return repository.save(product);
    }
    public Product updateProduct(Long id, Product product) {
        try {
            Product entity = repository.getReferenceById(id);
            updateData(entity, product);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    private void updateData(Product entity, Product updatedProduct) {
        entity.setName(updatedProduct.getName());
        entity.setDescription(updatedProduct.getDescription());
        entity.setPrice(updatedProduct.getPrice());
        entity.setAvailable(updatedProduct.getAvailable());
        if (updatedProduct.getCategories() != null) {
            entity.setCategories(updatedProduct.getCategories());
        }
    }
    
    public void deleteProductById(Long id) {
        
        if (repository.existsById(id)) {
            try {
                repository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DatabaseException(e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
    public List<Product> findProductByName (String name){
        return repository.findProductByName(name);
    }
    
}


package com.dev.apirest.services;

import com.dev.apirest.domain.Category;
import com.dev.apirest.repositories.CategoryRepository;
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
public class CategoryService {
    
    @Autowired
    private final CategoryRepository repository;
    
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    
    public List<Category> findAll() {
        return repository.findAll();
    }
    
    public Category findCategoryById(Long id) {
            Optional<Category> category = repository.findById(id);
        return category.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    
    public Category createCategory(Category category) {
        return repository.save(category);
    }
    
    public Category updateCategory(Long id, Category updatedCategory) {
        try {
            Category entity = repository.getReferenceById(id);
            updateData(entity, updatedCategory);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    private void updateData(Category entity, Category updatedCategory) {
        entity.setName(updatedCategory.getName());
    }
    
    public void deleteCategoryById(Long id) {
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
}
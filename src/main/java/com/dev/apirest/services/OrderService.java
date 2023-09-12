package com.dev.apirest.services;

import com.dev.apirest.domain.Order;
import com.dev.apirest.repositories.OrderRepository;
import com.dev.apirest.services.exceptions.DatabaseException;
import com.dev.apirest.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    
    private final OrderRepository repository;
    
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }
    
    public List<Order> findAll() {
        return repository.findAll();
    }
    
    public Order findOrderById(Long id) {
        Optional<Order> order = repository.findById(id);
        return order.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    
    public Order createOrder(Order order) {
        return repository.save(order);
    }
    
    public void deleteOrderById(Long id) {
        
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
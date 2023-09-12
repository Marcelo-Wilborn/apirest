package com.dev.apirest.services;



import com.dev.apirest.domain.User;
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
public class UserService {
    
    private final UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public List<User> findAll(){
        return repository.findAll();
    }
    public User findUserById(Long id){
        
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(()-> new ResourceNotFoundException(id));
    }
   
    public User createUser(User user) {
        return repository.save(user);
    }
    public User updateUser(Long id, User user) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, user);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    
    private void updateData(User entity, User updatedUser) {
        entity.setName(updatedUser.getName());
        entity.setEmail(updatedUser.getEmail());
        entity.setPhone(updatedUser.getPhone());
        entity.getAdress().setCity(updatedUser.getAdress().getCity());
        entity.getAdress().setState(updatedUser.getAdress().getState());
        entity.getAdress().setComplement(updatedUser.getAdress().getComplement());
        entity.getAdress().setPostalCode(updatedUser.getAdress().getPostalCode());
    }
    
    public void deleteUserById(Long id) {
        
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
        public List<User> findUserByName (String name){
            return repository.findUserByName(name);
        }
        
    }

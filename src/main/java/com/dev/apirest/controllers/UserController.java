package com.dev.apirest.controllers;

import com.dev.apirest.domain.User;
import com.dev.apirest.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    
    
    private final UserService service;
    
    public UserController(UserService service) {
        this.service = service;
    }
    
    
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
    List<User> list = service.findAll();
     return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user = service.findUserById(id);
        return ResponseEntity.ok().body(user);
    }
    
    @GetMapping(value="/findbyname/{name}")
    public List<User> findUserByName(@PathVariable String name) {
        return service.findUserByName(name);
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = service.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user = service.updateUser(id, user);
        return ResponseEntity.ok().body(user);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
   
}

package com.dev.apirest.domain;

import com.dev.apirest.controllers.CategoryController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
    
    public Category(String name){
        this.name = name;
    }
    
}

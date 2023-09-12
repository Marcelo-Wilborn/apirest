package com.dev.apirest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)


public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @Column(unique = true)
    private String name;
    
    @Column(unique = true)
    private String email;
    
    @Column(unique = true)
    private String phone;
    
    private String password;
    
    @Embedded
    private Address adress;
    
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders;
    
    
    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    
    
}


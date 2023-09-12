package com.dev.apirest.repositories;

import com.dev.apirest.domain.Product;
import com.dev.apirest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findProductByName(@Param("name") String name);
}

package com.dev.apirest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"items", "description","categories"})
    private Product product;
    
    private Integer quantity;
    
    private Double price;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "order_id")
    private Order order;
    
    public OrderItem(Order order, Product p, Integer quantity, double price){
        this.setProduct(p);
        this.quantity = quantity;
        this.price = price;
        this.setOrder(order);
    }
    
    public Double getSubTotal() {
        return price * quantity;
    }
    
}

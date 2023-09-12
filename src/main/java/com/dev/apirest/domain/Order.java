package com.dev.apirest.domain;


import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.dev.apirest.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)


public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC-3")
    private Instant moment;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"orders", "adress"})
    private User client;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();
    
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    public Order(Instant moment, OrderStatus orderStatus, User client) {
        this.moment = moment;
        this.client = client;
        setOrderStatus(orderStatus);
    }
    
    public Double getTotal(){
        double sum = 0.0;
        for (OrderItem x : items) {
            sum += x.getSubTotal();
        }
        return sum;
    }
    
    
}


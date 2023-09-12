package com.dev.apirest.domain;

import com.dev.apirest.enums.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Table(name = "Payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC-3")
    private Instant moment;
    
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;
    
    
}

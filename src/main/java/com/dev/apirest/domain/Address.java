package com.dev.apirest.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    private String postalCode;
    private String state;
    private String city;
    private String complement;
    
}

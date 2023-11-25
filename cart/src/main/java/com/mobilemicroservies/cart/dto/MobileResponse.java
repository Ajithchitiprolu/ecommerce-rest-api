package com.mobilemicroservies.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MobileResponse {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}
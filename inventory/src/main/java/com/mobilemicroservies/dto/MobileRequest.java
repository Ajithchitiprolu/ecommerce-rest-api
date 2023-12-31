package com.mobilemicroservies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MobileRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}

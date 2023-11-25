package com.mobilemicroservies.cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value="cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartMobile {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String mobileId;
    private String description;
    private Double price;
    private Integer quantity;

}


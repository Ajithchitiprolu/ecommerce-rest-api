package com.mobilemicroservies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value="mobile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mobile {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private Double price;
    private Integer stock;

}

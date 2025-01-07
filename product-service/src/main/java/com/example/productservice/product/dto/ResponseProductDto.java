package com.example.productservice.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDto {

    private String id;

    private String name;

    private Integer stock;

    private Long price;

}

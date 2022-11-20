package com.example.orderservice.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String id;
    private String description;
    private Integer price;
}

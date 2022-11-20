package com.example.orderservice.dto;

import lombok.Data;

@Data
public class PurchaseOrderRequestDto {
    private Integer userId;
    private String productId;
}

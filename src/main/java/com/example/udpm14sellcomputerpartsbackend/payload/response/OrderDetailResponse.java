package com.example.udpm14sellcomputerpartsbackend.payload.response;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private Long totalAmount;
    private Integer shipping;
//    private Integer quantity;
}

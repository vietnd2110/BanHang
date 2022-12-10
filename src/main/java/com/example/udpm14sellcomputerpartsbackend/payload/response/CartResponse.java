package com.example.udpm14sellcomputerpartsbackend.payload.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartResponse {
    Long totalAmount;
    Integer quantityCart;
}

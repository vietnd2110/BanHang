package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CartDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CartEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.CartResponse;

import java.util.List;

public interface CartService {
    List<CartEntity> getAllByUser(Long id);
    List<CartEntity> getAllCart();
    CartResponse sumTotalPriceAndQuantity(Long id);
    CartDto addToCart(CartDto cartDto);
    CartDto updateQuantity(Long idProduct, Integer quantity);
    void delete (Long id);
}

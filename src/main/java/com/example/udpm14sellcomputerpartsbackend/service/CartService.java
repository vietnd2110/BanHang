package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CartDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CartEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.CartResponse;

import java.util.Collection;
import java.util.List;

public interface CartService {
    Collection<CartEntity> getAllByUser(Long id);

    Collection<CartEntity> getAllCart();

    CartEntity getById(Long id);

    CartResponse sumTotalPriceAndQuantity(Long id);

    CartDto addToCart(CartDto cartDto);

    CartDto updateQuantity(Long idProduct, Integer quantity);

    void delete(Long id);

    void deleteAll();
}

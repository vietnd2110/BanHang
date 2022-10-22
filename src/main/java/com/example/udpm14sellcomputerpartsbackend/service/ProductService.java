package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;

import java.util.List;

public interface ProductService {
    List<ProductImageDto> findAll();
}

package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductImageDto> findAll();

    List<ProductImageDto> findAllByIDProduct(Long productId);

    Page<ProductImageDto> search(String name, Integer pageSize, Integer pageNumber);
    Page<ProductImageDto> findByCategory(Long id, Integer pageSize, Integer pageNumber);

    Page<ProductImageDto> getAllAndPage(Integer pageSize, Integer pageNumber);

    ProductDto create(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    void delete(Long id);
}

package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductEntity> findAll(Integer page, Integer pageNumber);

    ProductEntity getOne(Long productId);

    Page<ProductImageDto> findAllByIDProduct(Long productId, Integer page, Integer pageNumber);

    Page<ProductImageDto> search(String name, Integer pageSize, Integer pageNumber);

    Page<ProductImageDto> findByCategory(Long id, Integer pageSize, Integer pageNumber);

    Page<ProductImageDto> findByBrand(Long id, Integer pageSize, Integer pageNumber);

    Page<ProductImageDto> getAllAndPage(Integer pageSize, Integer pageNumber);

    ProductDto create(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    void delete(Long id);
}

package com.example.udpm14sellcomputerpartsbackend.service;


import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductRamDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.RamDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RamService {
    List<RamDto> findAll();

    Page<ProductRamDto> getAllProductRamWithCategoryId(Long categoryId, Integer page, Integer pageSize);

    List<ProductRamDto> getOneProductRamWithProductId(Long productId);

    List<RamDto> findAllByProduct(Long id);

    RamDto findById(Long id);

    RamDto create(RamDto hdDto);

    RamDto update(Long id, RamDto hdDto);

    void delete(Long id);
}

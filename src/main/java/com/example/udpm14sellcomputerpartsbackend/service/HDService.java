package com.example.udpm14sellcomputerpartsbackend.service;


import com.example.udpm14sellcomputerpartsbackend.model.dto.HDDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductHdDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HDService {
    Page<ProductHdDto> listProductHd(Long cateId, Integer page, Integer pageSize);

    List<ProductHdDto> getOneProductHd(Long productId);

    List<HDDto> findAll();

    List<HDDto> findAllByProduct(Long id);

    HDDto findById(Long id);

    HDDto create(HDDto hdDto);

    HDDto update(Long id, HDDto hdDto);

    void delete(Long id);
}

package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPsuDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.PsuDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PsuService {
    Page<ProductPsuDto> listProductPsu(Long cateId, Integer page, Integer pageSize);

    List<ProductPsuDto> getOneProductPsu(Long productId);

    List<PsuDto> findAll();

    List<PsuDto> findAllByProduct(Long id);

    PsuDto findById(Long id);

    PsuDto create(PsuDto psuDto);

    PsuDto update(Long id, PsuDto psuDto);

    void delete(Long id);
}

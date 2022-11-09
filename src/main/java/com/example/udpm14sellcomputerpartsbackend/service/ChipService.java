package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductChipDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChipService {

    //list product chip
    List<ProductChipDto> listProductChip(Long cateId);

    List<ChipDto> findAll();

    Page<ChipDto> findAllAndPage(Integer page, Integer page_size);

    List<ChipDto> findAllByProduct(Long id);

    ChipDto findById(Long id);

    ChipDto create(ChipDto chipDto);

    ChipDto update(Long id, ChipDto chipDto);

    void delete(Long id);
}

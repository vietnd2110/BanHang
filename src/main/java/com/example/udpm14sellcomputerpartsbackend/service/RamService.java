package com.example.udpm14sellcomputerpartsbackend.service;


import com.example.udpm14sellcomputerpartsbackend.model.dto.RamDto;

import java.util.List;

public interface RamService {
    List<RamDto> findAll();

    List<RamDto> findAllByProduct(Long id);

    RamDto findById(Long id);

    RamDto create(RamDto hdDto);

    RamDto update(Long id, RamDto hdDto);

    void delete(Long id);
}

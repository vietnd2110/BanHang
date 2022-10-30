package com.example.udpm14sellcomputerpartsbackend.service;


import com.example.udpm14sellcomputerpartsbackend.model.dto.HDDto;

import java.util.List;

public interface HDService {
    List<HDDto> findAll();

    List<HDDto> findAllByProduct(Long id);

    HDDto findById(Long id);

    HDDto create(HDDto hdDto);

    HDDto update(Long id, HDDto hdDto);

    void delete(Long id);
}

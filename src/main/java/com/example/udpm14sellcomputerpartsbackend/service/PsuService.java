package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.PsuDto;

import java.util.List;

public interface PsuService {
    List<PsuDto> findAll();

    List<PsuDto> findAllByProduct(Long id);

    PsuDto findById(Long id);

    PsuDto create(PsuDto psuDto);

    PsuDto update(Long id, PsuDto psuDto);

    void delete(Long id);
}

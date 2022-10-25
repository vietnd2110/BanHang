package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;

import java.util.List;

public interface ChipService {

    List<ChipDto> findAll();

    ChipDto create(ChipDto chipDto);

    ChipDto update(Long id, ChipDto chipDto);

    void delete(Long id);
}

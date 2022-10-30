package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.RamDto;

import java.util.List;

public interface RamService {
    List<RamDto> getByProduct(Long id);
    List<RamDto> getAll();
    RamDto getById(Long id);
    RamDto create(RamDto ramDto);
    RamDto update(Long id, RamDto ramDto);
    void delete(Long id);
}

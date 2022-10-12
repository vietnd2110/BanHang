package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.BrandDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.BrandEntity;

import java.util.List;

public interface BrandService {
    List<BrandEntity> getAll();
    BrandDto create(BrandDto brandDto);
    BrandDto update(Long id, BrandDto brandDto);
    BrandEntity delete(Long id);
}

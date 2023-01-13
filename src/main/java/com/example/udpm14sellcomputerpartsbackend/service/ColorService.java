package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.ColorEntity;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ColorDto;

import java.util.List;

public interface ColorService {
    List<ColorDto> getAll();
    ColorDto getById(Long id);

    ColorDto create(ColorDto colorDto);

    ColorDto update(Long id,ColorDto colorDto);

    void delete(Long id);
}

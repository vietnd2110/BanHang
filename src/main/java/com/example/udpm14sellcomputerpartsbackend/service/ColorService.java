package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.entity.ColorEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ColorRequest;

import java.util.List;

public interface ColorService {
    List<ColorEntity> getAll();

    ColorRequest create(ColorRequest colorRequest);

    ColorRequest update(ColorRequest colorRequest, Long id);

    void delete(Long id);
}

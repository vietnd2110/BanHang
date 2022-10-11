package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.model.entity.ColorEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ColorRequest;
import com.example.udpm14sellcomputerpartsbackend.repository.ColorRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ColorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    public final ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public List<ColorEntity> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public ColorRequest create(ColorRequest colorRequest) {
        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setColorName(colorRequest.getColorName());
        colorRepository.save(colorEntity);
        return colorRequest;
    }

    @Override
    public ColorRequest update(ColorRequest colorRequest, Long id) {
        ColorEntity colorEntity = colorRepository.findById(id).get();
        if (colorEntity == null) return null;
        colorEntity.setColorName(colorRequest.getColorName());
        colorRepository.save(colorEntity);
        return colorRequest;
    }


    @Override
    public void delete(Long id) {
        colorRepository.deleteById(id);
    }
}

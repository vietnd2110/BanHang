package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ColorEntity;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ColorDto;
import com.example.udpm14sellcomputerpartsbackend.repository.ColorRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ColorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    public final ColorRepository colorRepository;
    private final ModelMapper modelMapper;

    public ColorServiceImpl(ColorRepository colorRepository, ModelMapper modelMapper) {
        this.colorRepository = colorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ColorEntity> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public ColorDto getById(Long id) {
        ColorEntity colorEntity = colorRepository.findById(id).orElseThrow(()->
                new NotFoundException(HttpStatus.NOT_FOUND.value(), "Color id not found: "+id));
        return modelMapper.map(colorEntity,ColorDto.class);
    }

    @Override
    public ColorDto create(ColorDto colorDto) {
        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setColorName(colorDto.getColorName());
        colorRepository.save(colorEntity);
        return colorDto;
    }

    @Override
    public ColorDto update(Long id, ColorDto colorDto) {
        ColorEntity colorEntity = colorRepository.findById(id).orElseThrow(()
                -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Không tồn tại color id:" + id));
        colorEntity.setColorName(colorDto.getColorName());
        colorRepository.save(colorEntity);
        return colorDto;
    }


    @Override
    public void delete(Long id) {
        ColorEntity colorEntity = colorRepository.findById(id).orElseThrow(()
                -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Không tồn tại color id:" + id));
        colorRepository.deleteById(colorEntity.getId());
    }
}

package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ColorEntity;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ColorDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ColorRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ColorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    public final ColorRepository colorRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ColorServiceImpl(ColorRepository colorRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.colorRepository = colorRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ColorDto> getAll() {
        return colorRepository.getAll();
    }

    @Override
    public ColorDto getById(Long id) {
        ColorEntity findById = colorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Color id not found: " + id));

        return modelMapper.map(findById, ColorDto.class);
    }

    @Override
    public ColorDto create(ColorDto colorDto) {
        ProductEntity productEntity = productRepository.findById(colorDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+ colorDto.getProductId()));
        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setColorName(colorDto.getColorName());
        colorEntity.setProductId(productEntity.getId());
        colorRepository.save(colorEntity);
        return colorDto;
    }

    @Override
    public ColorDto update(Long id, ColorDto colorDto) {
        ColorEntity colorEntity = colorRepository.findById(id).orElseThrow(()
                -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Không tồn tại color id:" + id));
        ProductEntity productEntity = productRepository.findById(colorDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+ colorDto.getProductId()));
        colorEntity.setColorName(colorDto.getColorName());
        colorEntity.setProductId(productEntity.getId());
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

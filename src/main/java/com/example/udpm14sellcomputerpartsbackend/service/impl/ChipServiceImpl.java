package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ChipEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ChipRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ChipService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ChipServiceImpl implements ChipService {
    private final ChipRepository chipRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ChipServiceImpl(ChipRepository chipRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.chipRepository = chipRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ChipDto> findAll() {
        List<ChipEntity> chipEntityList = chipRepository.findAll();

        return chipEntityList.stream().map(chipEntity -> modelMapper
                .map(chipEntity, ChipDto.class)).collect(Collectors.toList());
    }

    @Override
    public ChipDto create(ChipDto chipDto) {
        ProductEntity productEntity = productRepository.findById(chipDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+chipDto.getProductId()));
        ChipEntity chip = modelMapper.map(chipDto, ChipEntity.class);

        return modelMapper.map(chipRepository.save(chip),ChipDto.class);
    }

    @Override
    public ChipDto update(Long id, ChipDto chipDto) {
        ProductEntity productEntity = productRepository.findById(chipDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+chipDto.getProductId()));
        ChipEntity findChip = chipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Chip id not found: " + id));
        ChipEntity chipEntity = modelMapper.map(chipDto, ChipEntity.class);
        chipEntity.setId(findChip.getId());

        return modelMapper.map(chipRepository.save(chipEntity),ChipDto.class);
    }

    @Override
    public void delete(Long id) {
        ChipEntity chip = chipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Chip id not found: " + id));
        chipRepository.deleteById(chip.getId());
    }
}

package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.RamDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.RamEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.RamRepository;
import com.example.udpm14sellcomputerpartsbackend.service.RamService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RamServiceImpl implements RamService {
    private final RamRepository repository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public RamServiceImpl(RamRepository repository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<RamDto> getByProduct(Long id) {
        List<RamEntity> listRamByProduct = repository.getAllByProductId(id);

        return listRamByProduct.stream().map(ramEntity -> modelMapper
                .map(ramEntity, RamDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RamDto> getAll() {
        List<RamEntity> list = repository.findAll();

        return list.stream().map(ramEntity -> modelMapper
                .map(ramEntity, RamDto.class)).collect(Collectors.toList());
    }

    @Override
    public RamDto getById(Long id) {
        RamEntity find = repository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND.value(), "Ram id not found: " + id));
        return modelMapper.map(find,RamDto.class);
    }

    @Override
    public RamDto create(RamDto ramDto) {
        ProductEntity productEntity = productRepository.findById(ramDto.getProductId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + ramDto.getProductId()));
        RamEntity ramEntity = modelMapper.map(ramDto, RamEntity.class);

        return modelMapper.map(repository.save(ramEntity),RamDto.class);
    }

    @Override
    public RamDto update(Long id, RamDto ramDto) {
        RamEntity find = repository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND.value(), "Ram id not found: " + id));
        ProductEntity productEntity = productRepository.findById(ramDto.getProductId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + ramDto.getProductId()));
        RamEntity ramEntity = modelMapper.map(ramDto, RamEntity.class);
        ramEntity.setId(find.getId());

        return modelMapper.map(repository.save(ramEntity),RamDto.class);
    }

    @Override
    public void delete(Long id) {
        RamEntity find = repository.findById(id).orElseThrow(() ->
                new NotFoundException(HttpStatus.NOT_FOUND.value(), "Ram id not found: " + id));
        repository.deleteById(find.getId());
    }
}

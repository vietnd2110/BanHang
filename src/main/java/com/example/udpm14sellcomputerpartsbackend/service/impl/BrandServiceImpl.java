package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.BrandDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.BrandEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.BrandRepository;
import com.example.udpm14sellcomputerpartsbackend.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandEntity> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public BrandDto create(BrandDto brandDto) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandName(brandDto.getBrandName());
        brandEntity.setDescription(brandDto.getDescription());
        brandRepository.save(brandEntity);
        return brandDto;
    }

    @Override
    public BrandDto update(Long id, BrandDto brandDto) {
        BrandEntity brandEntity = brandRepository.findById(id).orElseThrow(()
                -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Không tồn tại brand id:" + id));
        brandEntity.setBrandName(brandDto.getBrandName());
        brandEntity.setDescription(brandDto.getDescription());
        brandRepository.save(brandEntity);
        return brandDto;
    }

    @Override
    public void delete(Long id) {
        BrandEntity brandEntity = brandRepository.findById(id).orElseThrow(()
                -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Không tồn tại brand id:" + id));
        brandRepository.deleteById(brandEntity.getId());
    }
}

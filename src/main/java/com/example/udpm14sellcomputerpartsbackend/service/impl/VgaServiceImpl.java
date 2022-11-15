package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductVgaDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.VgaDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.VgaEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.VgaRepository;
import com.example.udpm14sellcomputerpartsbackend.service.VgaService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VgaServiceImpl implements VgaService {

    private final VgaRepository vgaRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;


    @Override
    public Page<ProductVgaDto> listProductVga(Long cateId, Integer page, Integer pageSize){
        return vgaRepository.listProductVga(cateId, PageRequest.of(page,pageSize));
    }

    @Override
    public List<ProductVgaDto> getOneProductVga(Long productId){
        return vgaRepository.getOneProductVga(productId);
    }

    public VgaServiceImpl(VgaRepository vgaRepository, ModelMapper modelMapper, ProductRepository productRepository) {
        this.vgaRepository = vgaRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<VgaDto> getAll() {
        List<VgaEntity> vgaEntity = vgaRepository.findAll();
        return vgaEntity.stream().map(vgaEntity1 -> modelMapper
                .map(vgaEntity1, VgaDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<VgaDto> getAllVgaByProduct(Long id) {
        List<VgaEntity> vgaEntity = vgaRepository.findByProductId(id);
        return vgaEntity.stream().map(vgaEntity1 -> modelMapper
                .map(vgaEntity1, VgaDto.class)).collect(Collectors.toList());
    }

    @Override
    public VgaDto getById(Long id) {
        VgaEntity vgaEntity = vgaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Vga id not found: " + id));
        return modelMapper.map(vgaRepository.findById(vgaEntity.getId()), VgaDto.class);
    }

    @Override
    public VgaDto create(VgaDto vgaDto) {
        ProductEntity findProduct = productRepository.findById(vgaDto.getProductId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + vgaDto.getProductId()));
        VgaEntity vgaEntity = modelMapper.map(vgaDto, VgaEntity.class);

        return modelMapper.map(vgaRepository.save(vgaEntity), VgaDto.class);
    }

    @Override
    public VgaDto update(Long id, VgaDto vgaDto) {
        VgaEntity find = vgaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Vga id not found: " + id));
        VgaEntity vgaEntity = modelMapper.map(vgaDto, VgaEntity.class);
        vgaEntity.setId(find.getId());
        return modelMapper.map(vgaRepository.save(vgaEntity), VgaDto.class);
    }

    @Override
    public void delete(Long id) {
        VgaEntity vgaEntity = vgaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Vga id not found: " + id));
        vgaRepository.deleteById(vgaEntity.getId());
    }
}

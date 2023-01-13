package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductRamDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.RamDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.RamEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.RamRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.RamService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RamServiceImpl implements RamService {

    private final RamRepository ramRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RamDto> findAll() {
        List<RamDto> ramEntityList = ramRepository.getAll();

        return ramEntityList;
    }


    @Override
    public Page<ProductRamDto> getAllProductRamWithCategoryId(Long categoryId, Integer page, Integer pageSize){
        return ramRepository.listProductRam(categoryId, PageRequest.of(page,pageSize));
    }

    @Override
    public List<ProductRamDto> getOneProductRamWithProductId(Long productId){
        return ramRepository.getOneProductRam(productId);
    }

    @Override
    public List<RamDto> findAllByProduct(Long id) {
        List<RamEntity> ramEntityList = ramRepository.findByProductId(id);

        return ramEntityList.stream().map(ramEntity -> modelMapper
                .map(ramEntity, RamDto.class)).collect(Collectors.toList());
    }

    @Override
    public RamDto findById(Long id) {
        RamEntity ram = ramRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Ram id not found: " + id));
        return modelMapper.map(ramRepository.findById(ram.getId()),RamDto.class);
    }

    @Override
    public RamDto create(RamDto ramDto) {
        ProductEntity productEntity = productRepository.findById(ramDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+ ramDto.getProductId()));
        RamEntity ram = modelMapper.map(ramDto, RamEntity.class);

        return modelMapper.map(ramRepository.save(ram),RamDto.class);
    }

    @Override
    public RamDto update(Long id, RamDto ramDto) {
        ProductEntity productEntity = productRepository.findById(ramDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+ ramDto.getProductId()));
        RamEntity findRam = ramRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Ram id not found: " + id));
        RamEntity ram = modelMapper.map(ramDto, RamEntity.class);
        ram.setId(findRam.getId());

        return modelMapper.map(ramRepository.save(ram),RamDto.class);
    }

    @Override
    public void delete(Long id) {
        RamEntity ram = ramRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Ram id not found: " + id));
        ramRepository.deleteById(ram.getId());
    }
}

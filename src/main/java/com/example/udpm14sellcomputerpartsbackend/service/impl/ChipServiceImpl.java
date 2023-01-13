package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ChipEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ChipRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ChipService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    //list product chip
    @Override
    public Page<ProductChipDto> listProductChip(Long cateId,Integer page, Integer pageSize){
        return chipRepository.listProductChip(cateId, PageRequest.of(page,pageSize));
    }

    @Override
    public List<ProductChipDto> getOneProductChip(Long productId){
        return chipRepository.getOneProductChip(productId);
    }

    @Override
    public List<ChipDto> findAll() {
        List<ChipDto> chipEntityList = chipRepository.getAll();

        return chipEntityList;
    }

    @Override
    public Page<ChipDto> findAllAndPage(Integer page, Integer page_size){
        Page<ChipDto> pages = chipRepository.getAll(PageRequest.of(page,page_size));
        return pages;
    }



    @Override
    public List<ChipDto> findAllByProduct(Long id) {
        List<ChipEntity> chipEntityList = chipRepository.findByProductId(id);

        return chipEntityList.stream().map(chipEntity -> modelMapper
                .map(chipEntity, ChipDto.class)).collect(Collectors.toList());
    }

    @Override
    public ChipDto findById(Long id) {
        ChipEntity chip = chipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Chip id not found: " + id));
        return modelMapper.map(chipRepository.findById(chip.getId()),ChipDto.class);
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

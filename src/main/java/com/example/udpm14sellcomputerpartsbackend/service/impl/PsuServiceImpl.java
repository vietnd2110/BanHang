package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductPsuDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.PsuDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PsuEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.PsuRepository;
import com.example.udpm14sellcomputerpartsbackend.service.PsuService;
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
public class PsuServiceImpl implements PsuService {

    private final PsuRepository psuRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<ProductPsuDto> listProductPsu(Long cateId, Integer page, Integer pageSize){
        return psuRepository.listProductPsu(cateId, PageRequest.of(page,pageSize));
    }

    @Override
    public List<ProductPsuDto> getOneProductPsu(Long productId){
        return psuRepository.getOneProductPsu(productId);
    }

    @Override
    public List<PsuDto> findAll() {
        List<PsuDto> psuEntityList = psuRepository.getAll();

        return psuEntityList;
    }

    @Override
    public List<PsuDto> findAllByProduct(Long id) {
        List<PsuEntity> psuEntityList = psuRepository.findByProductId(id);

        return psuEntityList.stream().map(psuEntity -> modelMapper
                .map(psuEntity, PsuDto.class)).collect(Collectors.toList());
    }

    @Override
    public PsuDto findById(Long id) {
        PsuEntity psu = psuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Psu id not found: " + id));
        return modelMapper.map(psuRepository.findById(psu.getId()),PsuDto.class);
    }

    @Override
    public PsuDto create(PsuDto psuDto) {
        ProductEntity productEntity = productRepository.findById(psuDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+ psuDto.getProductId()));
        PsuEntity psu = modelMapper.map(psuDto, PsuEntity.class);

        return modelMapper.map(psuRepository.save(psu),PsuDto.class);
    }

    @Override
    public PsuDto update(Long id, PsuDto psuDto) {
        ProductEntity productEntity = productRepository.findById(psuDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+ psuDto.getProductId()));
        PsuEntity findPsu = psuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Psu id not found: " + id));
        PsuEntity psuEntity = modelMapper.map(psuDto, PsuEntity.class);
        psuEntity.setId(findPsu.getId());

        return modelMapper.map(psuRepository.save(psuEntity),PsuDto.class);
    }

    @Override
    public void delete(Long id) {
        PsuEntity psu = psuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Psu id not found: " + id));
        psuRepository.deleteById(psu.getId());
    }
}

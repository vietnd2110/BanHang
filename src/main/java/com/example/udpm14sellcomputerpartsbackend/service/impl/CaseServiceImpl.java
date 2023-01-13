package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductCaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CaseEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.CaseRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.CaseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public CaseServiceImpl(CaseRepository caseRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.caseRepository = caseRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<ProductCaseDto> listProductCase(Long cateId, Integer page, Integer pageSize){
        return caseRepository.listProductCase(cateId, PageRequest.of(page,pageSize));
    }

    @Override
    public List<ProductCaseDto> getOneProductCase(Long productId){
        return caseRepository.getOneProductCase(productId);
    }


    @Override
    public List<CaseDto> getByProductId(Long id) {
        List<CaseEntity> caseEntities = caseRepository.findByProductId(id);

        return caseEntities.stream().map(caseEntity -> modelMapper
                .map(caseEntity, CaseDto.class)).collect(Collectors.toList());
    }

    @Override
    public CaseDto getById(Long id) {
        CaseEntity caseEntity = caseRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Case id not found: "+id));
        return modelMapper.map(caseRepository.findById(caseEntity.getId()),CaseDto.class);
    }

    @Override
    public List<CaseDto> getAll() {
        List<CaseDto> caseEntities = caseRepository.getAll();

        return caseEntities;
    }

    @Override
    public CaseDto create(CaseDto caseDto) {
        ProductEntity findProduct = productRepository.findById(caseDto.getProductId())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + caseDto.getProductId()));
        CaseEntity caseEntity = modelMapper.map(caseDto, CaseEntity.class);

        return modelMapper.map(caseRepository.save(caseEntity),CaseDto.class);
    }

    @Override
    public CaseDto update(Long id, CaseDto caseDto) {
        CaseEntity find = caseRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Case id not found: "+id));
        CaseEntity caseEntity = modelMapper.map(caseDto, CaseEntity.class);
        caseEntity.setId(find.getId());
        return modelMapper.map(caseRepository.save(caseEntity),CaseDto.class);
    }

    @Override
    public void delete(Long id) {
        CaseEntity caseEntity = caseRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Case id not found: "+id));
        caseRepository.deleteById(caseEntity.getId());
    }
}

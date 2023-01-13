package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.HDDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductHdDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.HdEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.HDRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.HDService;
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
public class HDServiceImpl implements HDService {

    private final HDRepository hdRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public Page<ProductHdDto> listProductHd(Long cateId, Integer page, Integer pageSize){
        return hdRepository.listProductHd(cateId, PageRequest.of(page,pageSize));
    }

    @Override
    public List<ProductHdDto> getOneProductHd(Long productId){
        return hdRepository.getOneProductHd(productId);
    }

    @Override
    public List<HDDto> findAll() {
        List<HDDto> hdEntityList = hdRepository.getAll();

        return hdEntityList;
    }

    @Override
    public List<HDDto> findAllByProduct(Long id) {
        List<HdEntity> hdEntityList = hdRepository.findByProductId(id);

        return hdEntityList.stream().map(hdEntity -> modelMapper
                .map(hdEntity, HDDto.class)).collect(Collectors.toList());
    }

    @Override
    public HDDto findById(Long id) {
        HdEntity hd = hdRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "HD id not found: " + id));
        return modelMapper.map(hdRepository.findById(hd.getId()),HDDto.class);
    }

    @Override
    public HDDto create(HDDto hdDto) {
        ProductEntity productEntity = productRepository.findById(hdDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+ hdDto.getProductId()));
        HdEntity hd = modelMapper.map(hdDto, HdEntity.class);

        return modelMapper.map(hdRepository.save(hd),HDDto.class);
    }

    @Override
    public HDDto update(Long id, HDDto hdDto) {
        ProductEntity productEntity = productRepository.findById(hdDto.getProductId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: "+ hdDto.getProductId()));
        HdEntity findHd = hdRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "HD id not found: " + id));
        HdEntity hdEntity = modelMapper.map(hdDto, HdEntity.class);
        hdEntity.setId(findHd.getId());

        return modelMapper.map(hdRepository.save(hdEntity),HDDto.class);
    }

    @Override
    public void delete(Long id) {
        HdEntity hd = hdRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "HD id not found: " + id));
        hdRepository.deleteById(hd.getId());
    }
}

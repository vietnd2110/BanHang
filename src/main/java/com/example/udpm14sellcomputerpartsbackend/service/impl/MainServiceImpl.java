package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.MainDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.*;
import com.example.udpm14sellcomputerpartsbackend.repository.*;
import com.example.udpm14sellcomputerpartsbackend.service.MainService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MainServiceImpl implements MainService {
    private final MainRepository mainRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final HDRepository hdRepository;
    private final ChipRepository chipRepository;
    private final RamRepository ramRepository;
    private final VgaRepository vgaRepository;

    public MainServiceImpl(MainRepository mainRepository, ModelMapper modelMapper, ProductRepository productRepository, HDRepository hdRepository, ChipRepository chipRepository, RamRepository ramRepository, VgaRepository vgaRepository) {
        this.mainRepository = mainRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.hdRepository = hdRepository;
        this.chipRepository = chipRepository;
        this.ramRepository = ramRepository;
        this.vgaRepository = vgaRepository;
    }

    @Override
    public List<MainDto> getAll() {
        List<MainEntity> mainEntityList = mainRepository.findAll();

        return mainEntityList.stream().map(mainEntity -> modelMapper.map(mainEntity, MainDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<MainDto> getAllAndPage(Integer pageSize, Integer pageNumber) {
        Page<MainEntity> mainEntityPage = mainRepository.getAllAndPage(PageRequest.of(pageSize, pageNumber));

        return mainEntityPage.map(mainEntity -> modelMapper.map(mainEntity, MainDto.class));
    }

    @Override
    public MainDto getById(Long id) {
        MainEntity finById = mainRepository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Main id not found: " + id));

        return modelMapper.map(mainRepository.findById(finById.getId()), MainDto.class);
    }

    @Override
    public Page<MainDto> getAllByProduct(Long id, Integer pageSize, Integer pageNumber) {
        ProductEntity find = productRepository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + id));
        Page<MainEntity> mainEntityPage = mainRepository.getAllByProductId(find.getId(), PageRequest.of(pageSize, pageNumber));
        return mainEntityPage.map(mainEntity -> modelMapper.map(mainEntity, MainDto.class));
    }


    @Override
    public MainDto create(MainDto mainDto) {
        check(mainDto.getHdId(), mainDto.getChipId(), mainDto.getProductId(), mainDto.getVgaId(), mainDto.getRamId());
        MainEntity mainEntity = modelMapper.map(mainDto, MainEntity.class);
        return modelMapper.map(mainRepository.save(mainEntity), MainDto.class);
    }

    @Override
    public MainDto update(Long id, MainDto mainDto) {
        MainEntity findMain = mainRepository.findById(mainDto.getId()).orElse(null);
        check(mainDto.getHdId(), mainDto.getChipId(), mainDto.getProductId(), mainDto.getVgaId(), mainDto.getRamId());
        MainEntity mainEntity = modelMapper.map(mainDto, MainEntity.class);
        mainEntity.setId(findMain.getId());
        return modelMapper.map(mainRepository.save(mainEntity), MainDto.class);
    }

    @Override
    public void delete(Long id) {
        MainEntity findMain = mainRepository.findById(id).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Main id not found: " + id));
        mainRepository.deleteById(findMain.getId());
    }

    public void check(Long hdId, Long chipId, Long productId, Long vgaId, Long ramId) {
        HdEntity hdEntity = hdRepository.findById(hdId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Hd id not found: " + hdId));
        ChipEntity chipEntity = chipRepository.findById(chipId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Chip id not found: " + chipId));
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + productId));
        VgaEntity vgaEntity = vgaRepository.findById(vgaId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "VgaEntity not found: " + hdId));
        RamEntity ramEntity = ramRepository.findById(ramId).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Ram not found: " + ramId));
    }
}

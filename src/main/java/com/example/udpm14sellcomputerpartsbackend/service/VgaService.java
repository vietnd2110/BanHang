package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductVgaDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.VgaDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VgaService {
    Page<ProductVgaDto> listProductVga(Long cateId, Integer page, Integer pageSize);

    List<ProductVgaDto> getOneProductVga(Long productId);

    List<VgaDto> getAll();
    List<VgaDto> getAllVgaByProduct(Long id);
    VgaDto getById(Long id);
    VgaDto create(VgaDto vgaDto);
    VgaDto update(Long id, VgaDto vgaDto);
    void delete(Long id);

}

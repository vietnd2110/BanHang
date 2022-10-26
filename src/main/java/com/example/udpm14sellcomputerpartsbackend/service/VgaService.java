package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.VgaDto;

import java.util.List;

public interface VgaService {
    List<VgaDto> getAll();
    VgaDto create(VgaDto vgaDto);
    VgaDto update(Long id, VgaDto vgaDto);
    void delete(Long id);

}

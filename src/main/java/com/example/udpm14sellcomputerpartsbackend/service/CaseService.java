package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CaseDto;

import java.util.List;

public interface CaseService {
    List<CaseDto> getByProductId(Long id);
    CaseDto getById(Long id);
    List<CaseDto> getAll();
    CaseDto create(CaseDto caseDto);
    CaseDto update(Long id, CaseDto caseDto);
    void delete(Long id);
}

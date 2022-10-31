package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.MainDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MainService {
    List<MainDto> getAll();
    Page<MainDto> getAllAndPage(Integer pageSize, Integer pageNumber);
    MainDto getById(Long id);
    Page<MainDto> getAllByProduct(Long id,Integer pageSize, Integer pageNumber);
    MainDto create(MainDto mainDto);
    MainDto update(Long id, MainDto mainDto);
    void delete(Long id);
}

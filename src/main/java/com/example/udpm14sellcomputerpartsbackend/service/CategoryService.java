package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CategoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();

    Page<CategoryDto> getAllAndPage(Integer pageSize, Integer pageNumber);

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void delete(Long id);
}

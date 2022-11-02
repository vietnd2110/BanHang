package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    CategoryDto getById(Long id);

    List<CategoryDto> getAllCategoryGroupId(Long groupId);

    List<CategoryDto> getAll();

    Page<CategoryDto> getAllAndPage(Integer pageSize, Integer pageNumber);

    String uploadImage(Long id, MultipartFile file);

    void delete(Long id);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto, MultipartFile file);

    CategoryDto createCategory(CategoryDto categoryDto, MultipartFile file);
}

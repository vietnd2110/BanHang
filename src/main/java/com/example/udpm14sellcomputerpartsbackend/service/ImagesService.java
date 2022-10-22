package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagesService {
    ImageEntity uploadImage(Long productId, MultipartFile file, String name);

    List<ImageProductDto> listImage();

    void deleteImage(Long id);
}

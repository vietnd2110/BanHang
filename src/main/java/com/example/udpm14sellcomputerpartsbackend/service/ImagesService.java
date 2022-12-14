package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagesService {
    ImageDto uploadImage(Long productId, MultipartFile file, ImageDto imageDto);

    ImageDto createImage(ImageDto imageDto, MultipartFile file);

    Page<ImageProductDto> listImage(Integer page, Integer pageSize);

    ImageProductDto listImagesId(Long id);

    void deleteImage(Long id);

    List<ProductImageDto> findAllByProductAndImages(Long productId);
    ProductImageDto ProductAndImagesByMaSp(String maSP);
}

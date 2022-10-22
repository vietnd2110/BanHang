package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.FolderContants;
import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ImageEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ImagesRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ImagesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {

    private final ProductRepository productRepository;
    private final ImagesRepository imagesRepository;
    private final CloudinaryServiceImpl cloudinaryService;

    public ImagesServiceImpl(
            ProductRepository productRepository,
            ImagesRepository imagesRepository,
            CloudinaryServiceImpl cloudinaryService
            ){
        this.productRepository = productRepository;
        this.imagesRepository = imagesRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public List<?> findAll(){
        return imagesRepository.findAll();
    }

    @Override
    public ImageEntity uploadImage(Long productId, MultipartFile file, String name){
        if(file.isEmpty()) throw new BadRequestException("File is not empty");
        if(name.isEmpty()) throw new BadRequestException("Name image is not empty");

        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(),"Product id not found: "+ productId));
        String link = cloudinaryService.uploadImage(file, FolderContants.PRODUCTS_IMAGES_FOLDER);

        ImageEntity imageEntity = new ImageEntity();
        System.out.println(productEntity.getId()+"da");
        imageEntity.setLink(link);
        imageEntity.setProduct_id(productId);
        imageEntity.setName(name);

        return imagesRepository.save(imageEntity);
    }

    @Override
    public List<ImageProductDto> listImage(){
        return imagesRepository.listImage();
    }

    @Override
    public void deleteImage(Long id){
        ImageEntity imageEntity = imagesRepository.findById(id).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(),"Image id not found: " + id));

        imagesRepository.deleteById(imageEntity.getId());
    }




}

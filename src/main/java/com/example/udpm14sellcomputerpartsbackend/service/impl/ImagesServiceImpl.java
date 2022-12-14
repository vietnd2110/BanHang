package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.FolderContants;
import com.example.udpm14sellcomputerpartsbackend.daos.ImageProductDao;
import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CategoryDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ImageEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ImagesRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ImagesService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {

    private final ProductRepository productRepository;
    private final ImagesRepository imagesRepository;
    private final CloudinaryServiceImpl cloudinaryService;
    private final ModelMapper modelMapper;
    private final ImageProductDao imageProductDao;

    public ImagesServiceImpl(
            ProductRepository productRepository,
            ImagesRepository imagesRepository,
            CloudinaryServiceImpl cloudinaryService,
            ModelMapper modelMapper,
            ImageProductDao imageProductDao
            ){
        this.productRepository = productRepository;
        this.imagesRepository = imagesRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
        this.imageProductDao = imageProductDao;
    }

    public List<?> findAll(){
        return imagesRepository.findAll();
    }

    @Override
    public ImageDto uploadImage(Long id, MultipartFile file, ImageDto imageDto){

        ImageEntity findByIdImage = imagesRepository.findById(imageDto.getId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(),"Id image not found: "+ id));

        ProductEntity productEntity = productRepository.findById(imageDto.getId())
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(),"Product id not found: "+ imageDto.getProduct_id()));


        ImageEntity imageEntity = modelMapper.map(imageDto,ImageEntity.class);

        String images = "";
        if(file != null){
            images = cloudinaryService.uploadImage(file,FolderContants.PRODUCTS_IMAGES_FOLDER);
            imageEntity.setLink(images);
        }else{
            imageEntity.setLink("https://res.cloudinary.com/ducnd1306/image/upload/v1667716002/sell-computer/images/avatars/eibisxdae7a3cysgkhpl.jpg");
        }

        imageEntity.setId(id);
        imageEntity.setProduct_id(imageDto.getProduct_id());
        imageEntity.setName(imageDto.getName());

        return  modelMapper.map(imagesRepository.save(imageEntity), ImageDto.class);
    }


    @Override
    public ImageDto createImage(ImageDto imageDto, MultipartFile file){

        ProductEntity product = productRepository.findById(imageDto.getProduct_id())
                .orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(),"Id product not found: "+ imageDto.getProduct_id()));

        ImageEntity imageEntity = modelMapper.map(imageDto,ImageEntity.class);


        String images = "";
        if(file != null){
            images = cloudinaryService.uploadImage(file,FolderContants.PRODUCTS_IMAGES_FOLDER);
            imageEntity.setLink(images);
        }else{
            imageEntity.setLink("https://res.cloudinary.com/ducnd1306/image/upload/v1667716002/sell-computer/images/avatars/eibisxdae7a3cysgkhpl.jpg");
        }
        imageEntity.setName(imageDto.getName());
        imageEntity.setProduct_id(imageDto.getProduct_id());

        return modelMapper.map(imagesRepository.save(imageEntity), ImageDto.class);
    }

    @Override
    public Page<ImageProductDto> listImage(Integer page, Integer pageSize){
        return imageProductDao.imageProduct(PageRequest.of(page,pageSize));
    }

    @Override
    public ImageProductDto listImagesId(Long id){
        ImageEntity imageEntity = imagesRepository.findById(id).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(),"Image id not found: " + id));
        return imagesRepository.listImagesId(id);
    }


    @Override
    public void deleteImage(Long id){
        ImageEntity imageEntity = imagesRepository.findById(id).
                orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(),"Image id not found: " + id));

        imagesRepository.deleteById(imageEntity.getId());
    }


    @Override
    public List<ProductImageDto> findAllByProductAndImages(Long productId){
        ProductEntity findById = productRepository.findById(productId)
                .orElseThrow(()->new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + productId));
        return imagesRepository.listProductAndImages(productId);
    }

    @Override
    public ProductImageDto ProductAndImagesByMaSp(String maSP) {
        return imagesRepository.ProductAndImagesByMaSp(maSP);
    }


}

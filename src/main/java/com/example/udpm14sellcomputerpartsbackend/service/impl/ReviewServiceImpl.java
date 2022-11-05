package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.ResponseStatusContants;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.exception.ObjectNotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ReviewDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ReviewEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ReviewRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.ReviewService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(
            ReviewRepository reviewRepository,
            ProductRepository productRepository,
            ModelMapper modelMapper
    ){
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ReviewEntity> reviewEntities(){
        return reviewRepository.findAll();
    }

    @Override
    public ReviewEntity getById(Long id) {
        return reviewRepository.findById(id).orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Review id not found:"+id));
    }

    @Override
    public List<ReviewEntity> reviewEntitiesProductId(Long productId){
        return reviewRepository.findAllByProductId(productId);
    }

    @Override
    public List<ReviewEntity> reviewEntityListAccountId(Long userId){
        return reviewRepository.findAllByUserId(userId);
    }


    @Override
    public ReviewDto create(Long productId, ReviewDto reviewDto){
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();

        ProductEntity findById = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.value(), "Product id not found: " + productId));

        System.out.println(detailService.getId() + "asd");
        ReviewEntity reviewEntity = modelMapper.map(reviewDto,ReviewEntity.class);
        reviewEntity.setProductId(productId);
        reviewEntity.setUserId(detailService.getId());

        return modelMapper.map(reviewRepository.save(reviewEntity),ReviewDto.class);

    }

    @Override
    public ReviewDto update(Long reviewId, ReviewDto reviewDto){
        CustomerDetailService detailService = CurrentUserUtils.getCurrentUserUtils();

        Optional<ReviewEntity> findById = reviewRepository.findById(reviewId);
        if(!findById.isPresent()){
            throw new ObjectNotFoundException(ResponseStatusContants.NOT_FOUND_REVIEW);
        }

        ReviewEntity reviewEntity = modelMapper.map(reviewDto,ReviewEntity.class);
        reviewEntity.setId(reviewId);
        reviewEntity.setUserId(detailService.getId());

        return modelMapper.map(reviewRepository.save(reviewEntity),ReviewDto.class);
    }


    @Override
    public void delete(Long reviewId){
        Optional<ReviewEntity> findById = reviewRepository.findById(reviewId);
        if(!findById.isPresent()){
            throw new ObjectNotFoundException(ResponseStatusContants.ERROR);
        }
        ReviewEntity reviewEntity = findById.get();
        reviewRepository.deleteById(reviewEntity.getId());
    }






}

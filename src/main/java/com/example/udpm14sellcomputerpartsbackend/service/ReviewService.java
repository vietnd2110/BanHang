package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ReviewDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ReviewEntity;

import java.util.List;

public interface ReviewService {
    List<ReviewEntity> reviewEntities();
    ReviewEntity getById(Long id);

    List<ReviewEntity> reviewEntitiesProductId(Long productId);

    List<ReviewEntity> reviewEntityListAccountId(Long userId);

    ReviewDto create(Long productId, ReviewDto reviewDto);

    ReviewDto update(Long reviewId, ReviewDto reviewDto);

    void delete(Long reviewId);
}

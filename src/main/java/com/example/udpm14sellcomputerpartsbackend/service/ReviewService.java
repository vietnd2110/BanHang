package com.example.udpm14sellcomputerpartsbackend.service;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ReviewDto;

public interface ReviewService {
    ReviewDto create(Long productId, ReviewDto reviewDto);

    ReviewDto update(Long reviewId, ReviewDto reviewDto);

    void delete(Long reviewId);
}

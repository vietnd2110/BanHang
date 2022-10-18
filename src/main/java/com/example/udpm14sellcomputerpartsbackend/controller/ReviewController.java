package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ReviewDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(
            ReviewService reviewService
    ){
        this.reviewService = reviewService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> create(
            @PathVariable("id") Long productId,
            @RequestBody ReviewDto reviewDto
            ){
        return ResponseEntity.ok(DefaultResponse.success(reviewService.create(productId,reviewDto)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long reviewId,
            @RequestBody ReviewDto reviewDto
    ){
        return ResponseEntity.ok(DefaultResponse.success(reviewService.update(reviewId,reviewDto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long reviewId
    ){
        reviewService.delete(reviewId);
      return  ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }




}

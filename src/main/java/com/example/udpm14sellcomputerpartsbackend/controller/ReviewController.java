package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ReviewDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ReviewEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/review")
@Tag(
        description = "Review controller",
        name = "Các api về đăng nhập, đăng xuất"
)
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(
            ReviewService reviewService
    ) {
        this.reviewService = reviewService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> listReview() {
        List<ReviewEntity> reviewEntityList = reviewService.reviewEntities();
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get all success")
                .data(reviewEntityList)
                .build();
        return ResponseEntity.ok(sampleResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        ReviewEntity reviewEntity = reviewService.getById(id);
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get by id success")
                .data(reviewEntity)
                .build();
        return ResponseEntity.ok(sampleResponse);
    }


    @GetMapping("/list-product-id/{id}")
    public ResponseEntity<?> listReviewProductId(
            @PathVariable("id") Long productId
    ) {
        List<ReviewEntity> reviewEntityList = reviewService.reviewEntitiesProductId(productId);
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get all success")
                .data(reviewEntityList)
                .build();
        return ResponseEntity.ok(sampleResponse);
    }

    @GetMapping("/list-user-id/{id}")
    public ResponseEntity<?> listReviewUserId(
            @PathVariable("id") Long userId
    ) {
        List<ReviewEntity> reviewEntities = reviewService.reviewEntityListAccountId(userId);
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get all success")
                .data(reviewEntities)
                .build();
        return ResponseEntity.ok(sampleResponse);
    }


    @Operation(summary = "Thêm đánh giá sản phẩm", description = "Thêm đánh giá sản phẩm")
    @PostMapping("/create/{id}")
    public ResponseEntity<?> create(
            @PathVariable("id") Long productId,
            @RequestBody ReviewDto reviewDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(reviewService.create(productId, reviewDto)));
    }

    @Operation(summary = "Cập nhật đánh giá sản phẩm", description = "Cập nhật đánh giá sản phẩm")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long reviewId,
            @RequestBody ReviewDto reviewDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(reviewService.update(reviewId, reviewDto)));
    }

    @Operation(summary = "Xóa đánh giá sản phẩm", description = "Xóa đánh giá sản phẩm")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long reviewId
    ) {
        reviewService.delete(reviewId);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }


}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.FavoriteDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.FavoriteEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/favorite")
@Tag(
        description = "Favorite controller",
        name = "Các api yêu thích sản phẩm"
)
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }


    @GetMapping("/product-favorite")
    public ResponseEntity<?> listProductFavorite(){
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get all success")
                .data(favoriteService.listProductFavoritte())
                .build();
        return ResponseEntity.ok(sampleResponse);
    }


    @GetMapping("/list")
    public ResponseEntity<?> findAll(
    ){
        List<FavoriteDto> favoriteEntityList = favoriteService.favoriteProducts();
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get all success")
                .data(favoriteEntityList)
                .build();
        return ResponseEntity.ok(sampleResponse);
    }



    @GetMapping("/list-product-id/{id}")
    public ResponseEntity<?> findAllFavoriteProductId(
            @PathVariable("id") Long productId
    ){
        List<FavoriteEntity> favoriteEntityList = favoriteService.listFavoriteProductId(productId);
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get all favorite with id product")
                .data(favoriteEntityList)
                .build();
        return ResponseEntity.ok(sampleResponse);
    }

    @GetMapping("/list-account-id/{id}")
    public ResponseEntity<?> findAllFavoriteAccountId(
            @PathVariable("id") Long accountId
    ){
        List<FavoriteEntity> favoriteEntityList = favoriteService.listFavoriteAccountId(accountId);
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get all favorite with id account")
                .data(favoriteEntityList)
                .build();
        return ResponseEntity.ok(sampleResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findFavoriteId(
            @PathVariable("id") Long id
    ){
        FavoriteEntity favoriteEntityList = favoriteService.findById(id);
        SampleResponse sampleResponse = SampleResponse
                .builder()
                .success(true)
                .message("Get id favorite")
                .data(favoriteEntityList)
                .build();
        return ResponseEntity.ok(sampleResponse);
    }


    @Operation(summary = "Thêm sản phẩm yêu thích", description = "Thêm sản phẩm yêu thích")
    @PostMapping("/{id}")
    public ResponseEntity<?> likeProduct(
            @PathVariable("id") Long productId
    ){
      return ResponseEntity.ok(DefaultResponse.success(favoriteService.favoriteProduct(productId)));
    }

    @Operation(summary = "Xóa sản phẩm yêu thích", description = "Xóa sản phẩm yêu thích")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id
    ){
        favoriteService.deleteFavorite(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

    @Operation(summary = "đếm số lượng yêu thích theo userId", description = "đếm số lượng yêu thích theo userId")
    @GetMapping("/count-favorite")
    public ResponseEntity<?> countFavorite(){
        return ResponseEntity.ok(DefaultResponse.success(favoriteService.countFavorite()));
    }



}

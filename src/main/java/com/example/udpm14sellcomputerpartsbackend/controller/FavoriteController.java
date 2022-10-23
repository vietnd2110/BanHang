package com.example.udpm14sellcomputerpartsbackend.controller;


import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
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




}

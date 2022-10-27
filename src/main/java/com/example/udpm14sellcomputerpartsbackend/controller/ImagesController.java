package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ImagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/images")
@Tag(
        description = "Images controller",
        name = "Các api quản lý image"
)
public class ImagesController {

    private final ImagesService imagesService;

    public ImagesController(ImagesService imagesService){
        this.imagesService = imagesService;
    }

    @Operation(summary = "Danh sách images", description = "Danh sách image")
    @GetMapping("/list")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(DefaultResponse.success(imagesService.listImage()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllById(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(DefaultResponse.success(imagesService.listImagesId(id)));
    }

    @Operation(summary = "Thêm mới images theo id của product", description = "Thêm mới images theo id của product")
    @PostMapping(value = "/upload-image/{id}",consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(
            @PathVariable("id") Long productId,
            @Valid @RequestBody  MultipartFile file,
            @RequestParam("name") String name
    ){
        return ResponseEntity.ok(DefaultResponse.success(imagesService.uploadImage(productId, file, name)));
    }

    @Operation(summary = "Xóa images", description = "Xóa images")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id){
        imagesService.deleteImage(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success !"));
    }



}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ImageEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ImagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/images")
@Tag(
        description = "Images controller",
        name = "Các api quản lý image"
)
public class ImagesController {

    private final ImagesService imagesService;

    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @PreAuthorize("permitAll()")
    @Operation(summary = "Danh sách images", description = "Danh sách image")
    @GetMapping("/list")
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "page-number", required = false) Integer pageNumber
    ) {
        return ResponseEntity.ok(DefaultPagingResponse.success(imagesService.listImage(page, pageNumber)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAllById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(DefaultResponse.success(imagesService.listImagesId(id)));
    }

    @Operation(summary = "Thêm mới images theo id của product", description = "Thêm mới images theo id của product")
    @PutMapping(value = "/upload-image/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(
            @PathVariable("id") Long productId,
            @RequestParam(required = false) MultipartFile file,
            @ModelAttribute ImageDto imageDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(imagesService.uploadImage(productId, file, imageDto)));
    }

    @Operation(summary = "Xóa images", description = "Xóa images")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) {
        imagesService.deleteImage(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success !"));
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @ModelAttribute ImageDto imageDto,
            @RequestParam(required = false) MultipartFile file
    ) {
        return ResponseEntity.ok(
                DefaultResponse.success(imagesService.createImage(imageDto, file)));
    }


    @Operation(summary = "Lấy tất cả danh sách san phẩm product và ảnh theo id product bên images ", description = "Lấy tất cả danh sách san phẩm product và ảnh theo id product bên images ")
    @PreAuthorize("permitAll()")
    @GetMapping("/product-id/{id}")
    public ResponseEntity<?> findAllByProductAndImages(
            @PathVariable("id") Long id
    ) {
        List<ProductImageDto> productImageDtos = imagesService.findAllByProductAndImages(id);
        SampleResponse response = SampleResponse
                .builder()
                .success(true)
                .message("Get by id product success")
                .data(productImageDtos)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Lấy tất cả danh sách san phẩm product và ảnh theo maSP product bên images ", description = "Lấy tất cả danh sách san phẩm product và ảnh theo id product bên images ")
    @PreAuthorize("permitAll()")
    @GetMapping("/product-maSP/{ma}")
    public ResponseEntity<?> listProductAndImagesByMaSp(
            @PathVariable("ma") String maSP
    ) {
        ProductImageDto productImageDtos = imagesService.ProductAndImagesByMaSp(maSP);
        SampleResponse response = SampleResponse
                .builder()
                .success(true)
                .message("Get by maSP product success")
                .data(productImageDtos)
                .build();
        return ResponseEntity.ok(response);
    }
}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.BrandDto;
import com.example.udpm14sellcomputerpartsbackend.payload.request.GroupComponent;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity getAllBrand() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Brand ")
                .data(brandService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Xóa component thành công")
                .data(null)
                .build();
        brandService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<?> createBrand(@Valid @RequestBody BrandDto brandDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thêm brand thành công")
                .data(brandService.create(brandDto))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@RequestBody @Valid BrandDto brandDto,@PathVariable Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Cập nhập brand thành công")
                .data(brandService.update(id,brandDto))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.BrandDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/brand")
@Tag(
        description = "Brand controller",
        name = "Các api về hãng sản xuất"
)
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "Lấy tất cả danh sách về hãng sản xuất", description = "Lấy tất cả danh sách về hãng sản xuất")
    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity getAllBrand() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Brand ")
                .data(brandService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id")Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Brand theo id")
                .data(brandService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "xóa hãng sản xuất", description = "xóa hãng sản xuất")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Xóa brand thành công")
                .data(null)
                .build();
        brandService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Thêm mới hãng sản xuất", description = "Thêm mới hãng sản xuất")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping()
    public ResponseEntity<?> createBrand(@Valid @RequestBody BrandDto brandDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thêm brand thành công")
                .data(brandService.create(brandDto))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Cập nhật hãng sản xuất", description = "Cập nhật hãng sản xuất")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
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

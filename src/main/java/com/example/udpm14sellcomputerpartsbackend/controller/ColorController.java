package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.BrandDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ColorDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ColorService;
import com.example.udpm14sellcomputerpartsbackend.ultil.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/color")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public ResponseEntity getAllColor() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Color ")
                .data(colorService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getColorById(@PathVariable Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Color ")
                .data(colorService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping()
    public ResponseEntity<?> createBrand(@Valid @RequestBody ColorDto colorDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thêm color thành công")
                .data(colorService.create(colorDto))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Xóa color thành công")
                .data(null)
                .build();
        colorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateColor(@RequestBody @Valid ColorDto colorDto,@PathVariable Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Cập nhập color thành công")
                .data(colorService.update(id,colorDto))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.BrandDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ColorDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ColorService;
import com.example.udpm14sellcomputerpartsbackend.ultil.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get Color by id")
                .data(colorService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<?> createBrand(@Valid @RequestBody ColorDto colorDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thêm color thành công")
                .data(colorService.create(colorDto))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateColor(@RequestBody ColorDto colorDto,@PathVariable Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Cập nhập color thành công")
                .data(colorService.update(id,colorDto))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

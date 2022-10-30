package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.HDDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.HDService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hd")
@Tag(
        description = "HD controller",
        name = "Các api về hd"
)
public class HDController {
    private final HDService hdService;

    @GetMapping
    public ResponseEntity getAllHd() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All HD")
                .data(hdService.findAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getHdById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get HD By Id")
                .data(hdService.findById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getAllHdByProduct(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All HD By Product")
                .data(hdService.findAllByProduct(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody HDDto hdDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(hdService.create(hdDto)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody HDDto hdDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(hdService.update(id, hdDto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        hdService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}
package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.PsuDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.PsuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/psu")
@Tag(
        description = "Psu controller",
        name = "Các api về psu"
)
public class PsuController {
    private final PsuService psuService;

    @GetMapping
    public ResponseEntity getAllPsu() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Psu")
                .data(psuService.findAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity getPsuById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get Psu By Id")
                .data(psuService.findById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity getAllPsuByProduct(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Psu By Product")
                .data(psuService.findAllByProduct(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody PsuDto psuDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(psuService.create(psuDto)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody PsuDto psuDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(psuService.update(id, psuDto)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        psuService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}
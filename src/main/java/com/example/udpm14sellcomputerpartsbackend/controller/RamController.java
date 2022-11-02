package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.RamDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.RamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ram")
@Tag(
        description = "Ram controller",
        name = "Các api về ram"
)
public class RamController {
    private final RamService ramService;

    @GetMapping
    public ResponseEntity getAllHd() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Ram")
                .data(ramService.findAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRamById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get Ram By Id")
                .data(ramService.findById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getAllHdByProduct(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Ram By Product")
                .data(ramService.findAllByProduct(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody RamDto ramDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(ramService.create(ramDto)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody RamDto ramDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(ramService.update(id, ramDto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        ramService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}
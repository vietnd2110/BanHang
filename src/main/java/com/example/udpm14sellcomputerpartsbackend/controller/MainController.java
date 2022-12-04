package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CategoryDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.MainDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.MainService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/main")
@Tag(description = "Main controller", name = "Các api về Main")
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllMain() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Main")
                .data(mainService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllAndPage(
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber) {
        Page<MainDto> page = mainService.getAllAndPage(pageSize, pageNumber);
        return ResponseEntity.ok(DefaultPagingResponse.success(page));
    }


    @GetMapping("product/{id}")
    public ResponseEntity<?> getAllMainByProduct(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Main By Product id")
                .data(mainService.getAllByProduct(id, pageSize, pageNumber))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getMainById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get Main by id")
                .data(mainService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody MainDto mainDto) {
        return ResponseEntity.ok(DefaultResponse.success(mainService.create(mainDto)));
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody MainDto mainDto) {
        return ResponseEntity.ok(DefaultResponse.success(mainService.update(id, mainDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mainService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}

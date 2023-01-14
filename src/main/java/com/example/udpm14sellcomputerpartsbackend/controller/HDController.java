package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.HDDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.HDRepository;
import com.example.udpm14sellcomputerpartsbackend.service.HDService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/hd")
@Tag(
        description = "HD controller",
        name = "Các api về hd"
)
public class HDController {
    private final HDService hdService;
    private final HDRepository hdRepository;

    @GetMapping("/product-hd/{id}")
    public ResponseEntity<?> listProductHd(
            @PathVariable("id") Long categoryId,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "page-size") Integer pageSize
    ){
        return ResponseEntity.ok(DefaultPagingResponse.success(hdService.listProductHd(categoryId,page,pageSize)));
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneProductHd(
            @PathVariable("id") Long productId
    ){
        return ResponseEntity.ok(DefaultResponse.success(hdService.getOneProductHd(productId)));
    }

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

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody HDDto hdDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(hdService.create(hdDto)));
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody HDDto hdDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(hdService.update(id, hdDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        hdService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

    @GetMapping("/list-hd")
    public ResponseEntity<?> listProductHd() {
        return ResponseEntity.ok(DefaultResponse.success(hdRepository.listCateProductHdDto()));
    }
}
package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.PsuDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.PsuRepository;
import com.example.udpm14sellcomputerpartsbackend.service.PsuService;
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
@RequestMapping("/api/v1/psu")
@Tag(
        description = "Psu controller",
        name = "Các api về psu"
)
public class PsuController {
    private final PsuService psuService;
    private final PsuRepository psuRepository;

    @GetMapping("/product-psu/{id}")
    public ResponseEntity<?> listProductPsu(
            @PathVariable("id") Long categoryId,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "page-size") Integer pageSize
    ){
        return ResponseEntity.ok(DefaultPagingResponse.success(psuService.listProductPsu(categoryId,page,pageSize)));
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneProductPsu(
            @PathVariable("id") Long productId
    ){
        return ResponseEntity.ok(DefaultResponse.success(psuService.getOneProductPsu(productId)));
    }

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

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody PsuDto psuDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(psuService.create(psuDto)));
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody PsuDto psuDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(psuService.update(id, psuDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        psuService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

    @GetMapping("/list-psu")
    public ResponseEntity<?> listPsu() {
        return ResponseEntity.ok(DefaultResponse.success(psuRepository.listCateProductPsuDto()));
    }
}
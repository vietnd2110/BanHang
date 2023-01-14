package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.ChipRepository;
import com.example.udpm14sellcomputerpartsbackend.service.ChipService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/chip")
@Tag(
        description = "Chip controller",
        name = "Các api về chip"
)
@AllArgsConstructor
public class ChipController {
    private final ChipService chipService;
    private final ChipRepository chipRepository;



    @PreAuthorize("permitAll()")
    @GetMapping("/product-chip/{id}")
    public ResponseEntity<?> listProductChip(
            @PathVariable("id") Long categoryId,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "page-size") Integer pageSize
    ){
        return ResponseEntity.ok(DefaultPagingResponse.success(chipService.listProductChip(categoryId,page,pageSize)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneProductChip(
            @PathVariable("id") Long productId
    ){
        return ResponseEntity.ok(DefaultResponse.success(chipService.getOneProductChip(productId)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<?> getAllChipandPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam("page-number") Integer pageNumber
    ){
       return ResponseEntity.ok(DefaultPagingResponse.success(chipService.findAllAndPage(page,pageNumber)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public ResponseEntity getAllChip() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Chip")
                .data(chipService.findAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity getChipById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get Chip By Id")
                .data(chipService.findById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/product/{id}")
    public ResponseEntity getAllChipByProduct(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Chip By Product")
                .data(chipService.findAllByProduct(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public ResponseEntity<?> create(
            @Valid @RequestBody ChipDto chipDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(chipService.create(chipDto)));
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ChipDto chipDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(chipService.update(id, chipDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        chipService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

    @GetMapping("/list-chip")
    public ResponseEntity<?> listCategoryProductChip() {
        return ResponseEntity.ok(DefaultResponse.success(chipRepository.listCateProductChipDto()));
    }

}

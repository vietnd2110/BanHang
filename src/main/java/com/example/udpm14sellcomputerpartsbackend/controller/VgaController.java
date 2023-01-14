package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.VgaDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.VgaRepository;
import com.example.udpm14sellcomputerpartsbackend.service.VgaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/vga")
@Tag(
        description = "Vga controller",
        name = "Các api về Vga"
)
@AllArgsConstructor
public class VgaController {
    private final VgaService vgaService;
    private final VgaRepository vgaRepository;


    @GetMapping("/product-vga/{id}")
    public ResponseEntity<?> listProductVga(
            @PathVariable("id") Long categoryId,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "page-size") Integer pageSize
    ){
        return ResponseEntity.ok(DefaultPagingResponse.success(vgaService.listProductVga(categoryId,page,pageSize)));
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneProductVga(
            @PathVariable("id") Long productId
    ){
        return ResponseEntity.ok(DefaultResponse.success(vgaService.getOneProductVga(productId)));
    }

    @GetMapping
    public ResponseEntity getAllVga() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Vga")
                .data(vgaService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getVgaById(@PathVariable Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get Vga By id")
                .data(vgaService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("product/{id}")
    public ResponseEntity getAllVgaByProduct(@PathVariable Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Vga By Product ")
                .data(vgaService.getAllVgaByProduct(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody VgaDto vgaDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(vgaService.create(vgaDto)));
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VgaDto vgaDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(vgaService.update(id, vgaDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        vgaService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

    @GetMapping("/list-vga")
    public ResponseEntity<?> listCaVga() {
        return ResponseEntity.ok(DefaultResponse.success(vgaRepository.listCateProductVgaDto()));
    }
}

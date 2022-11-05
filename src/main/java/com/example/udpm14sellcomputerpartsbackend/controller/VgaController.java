package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.ChipDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.VgaDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.VgaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/vga")
@Tag(
        description = "Vga controller",
        name = "Các api về Vga"
)
public class VgaController {
    private final VgaService vgaService;

    public VgaController(VgaService vgaService) {
        this.vgaService = vgaService;
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

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody VgaDto vgaDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(vgaService.create(vgaDto)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VgaDto vgaDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(vgaService.update(id, vgaDto)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        vgaService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}
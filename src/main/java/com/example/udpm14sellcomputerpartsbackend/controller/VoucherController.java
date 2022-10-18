package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.VoucherDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.VoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/voucher")
public class VoucherController {
    private final VoucherService voucherService;
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Get all Voucher")
                .data(voucherService.getAll())
                .build());
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody VoucherDto voucherDto) {
        return ResponseEntity.ok(DefaultResponse.success(voucherService.create(voucherDto)));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody VoucherDto voucherDto) {
        return ResponseEntity.ok(DefaultResponse.success(voucherService.update(id, voucherDto)));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        voucherService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}
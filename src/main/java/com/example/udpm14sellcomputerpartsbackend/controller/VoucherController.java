package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.VoucherDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.VoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Get Voucher By Id")
                .data(voucherService.getById(id))
                .build());
    }


    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody VoucherDto voucherDto) {
        return ResponseEntity.ok(DefaultResponse.success(voucherService.create(voucherDto)));
    }


    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody VoucherDto voucherDto) {
        return ResponseEntity.ok(DefaultResponse.success(voucherService.update(id, voucherDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        voucherService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}
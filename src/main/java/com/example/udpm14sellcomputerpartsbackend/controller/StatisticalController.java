package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.StatisticalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/thong-ke")
public class StatisticalController {

    @Autowired
    private StatisticalService service;

    @Operation(summary = "lấy danh sách hóa đơn và doanh thu các năm", description = "")
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê hóa đơn và doanh thu năm")
                .data(service.listHoaDonCacNam())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy danh sách hóa đơn và doanh thu theo năm", description = "")
    @GetMapping("/list/{year}")
    public ResponseEntity<?> listByYear(@PathVariable("year") Integer year) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê hóa đơn và doanh thu theo năm")
                .data(service.listThongKeTheoNam(year))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy danh sách hóa đơn và doanh thu theo tháng và năm", description = "")
    @GetMapping("/list/month")
    public ResponseEntity<?> listByYear(
            @RequestParam(value = "month") Integer month,
            @RequestParam(value = "year")  Integer year
    ) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê hóa đơn và doanh thu theo tháng và nam")
                .data(service.listThongKeTheoThangVaNam(year, month))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

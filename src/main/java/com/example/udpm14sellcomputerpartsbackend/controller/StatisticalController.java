package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.daos.StatisticalDao;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.OrderRepository;
import com.example.udpm14sellcomputerpartsbackend.service.StatisticalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/thong-ke")
public class StatisticalController {

    @Autowired
    private StatisticalService service;

    @Autowired
    private StatisticalDao statisticalDao;

    @Autowired
    private OrderRepository orderRepository;

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
    @Operation(summary = "lấy danh sách hóa đơn và doanh thu từng ngày theo tháng và năm", description = "")
    @GetMapping("/listHoaDonTungNgayTheoThangVaNam")
    public ResponseEntity<?> listHoaDonTungNgayTheoThangVaNam(@RequestParam(value = "month") Integer month,
                                                              @RequestParam(value = "year")  Integer year) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("lấy danh sách hóa đơn và doanh thu từng ngày theo tháng và năm")
                .data(orderRepository.listHoaDonTungNgayTheoThangVaNam(year,month))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(summary = "lấy danh sách hóa đơn và doanh thu từng tháng theo năm", description = "")
    @GetMapping("/listHoaDonTungThangTheoNam")
    public ResponseEntity<?> listHoaDonTungThangTheoNam(@RequestParam(value = "year") Integer year) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("lấy danh sách hóa đơn và doanh thu từng tháng theo năm")
                .data(orderRepository.listHoaDonTungThangTheoNam(year))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Thống kê số đơn hàng và doanh thu tu trước tới nay", description = "")
    @GetMapping("/ThongKeTuTruocToiNay")
    public ResponseEntity<?> thongKeTuTruocToiNay() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê số đơn hàng và doanh thu tu trước tới nay")
                .data(orderRepository.thongKeTuTruocToiNay())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(summary = "Thống kê trạng thái đơn hàng", description = "")
    @GetMapping("/ThongKeTrangThaiDonHang")
    public ResponseEntity<?> ThongKeTrangThaiDonHang() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê trạng thái đơn hàng")
                .data(service.thongKeTrangThaiDonHang())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy danh sách hóa đơn và doanh thu các năm", description = "")
    @GetMapping("/list/month-year")
    public ResponseEntity<?> listThangVaNam() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê hóa đơn và doanh thu tháng + năm")
                .data(service.listHoaDonCacThangVaNam())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy danh sách hóa đơn và doanh thu ngày hiện tại", description = "")
    @GetMapping("/dateNow")
    public ResponseEntity<?> dateNow() {
        Integer yearNow = LocalDateTime.now().getYear();
        Integer monthNow = LocalDateTime.now().getMonthValue();
        Integer dayNow = LocalDateTime.now().getDayOfMonth();
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê hóa đơn và doanh thu ngày hiện tại")
                .data(orderRepository.listHoaDonTheoNgayHienTai(yearNow,monthNow,dayNow))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy danh sách sản phẩm bán chạy", description = "")
    @GetMapping("/top-product/{Soluong}")
    public ResponseEntity<?> topSanPhamBanChay(@PathVariable("Soluong") Integer soLuong) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê  sản phẩm bán chạy")
                .data(orderRepository.topSanPhamBanChay(Pageable.ofSize(soLuong)))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(summary = "lấy 10 danh sách sản phẩm bán chạy", description = "")
    @GetMapping("/top-10-product")
    public ResponseEntity<?> top10SanPhamBanChay() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thống kê  sản phẩm bán chạy")
                .data(orderRepository.topSanPhamBanChay(Pageable.ofSize(10)))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

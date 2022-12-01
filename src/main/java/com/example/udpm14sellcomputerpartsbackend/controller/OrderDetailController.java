package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.OrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/orderDetail")
@Tag(description = "OrderDetail controller", name = "Các api về OrderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @Operation(summary = "lấy tất cả danh sách", description = "")
    @GetMapping("/list")
    public ResponseEntity<?> getAllOrderDetail() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All OrderDetail")
                .data(orderDetailService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy tất cả danh sách và phân trang", description = "")
    @GetMapping
    public ResponseEntity<?> getAllOrderDetailAndPage(
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber) {
        Page<OrderDetailDto> page = orderDetailService.getAllAndPage(pageSize, pageNumber);
        return ResponseEntity.ok(DefaultPagingResponse.success(page));
    }

    @Operation(summary = "lấy tất cả danh sách và phân trang theo order", description = "")
    @GetMapping("order/{id}")
    public ResponseEntity<?> getAllOrderDetailByOrder(
            @PathVariable("id") Long id,
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All OrderDetail By Order id")
                .data(orderDetailService.getByOrder(id, pageSize, pageNumber))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy tất cả danh sách và phân trang theo user id", description = "")
    @GetMapping("user/{userID}")
    public ResponseEntity<?> getAllOrderDetailByUserId(
            @PathVariable("userID") Long id,
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All OrderDetail By user id")
                .data(orderDetailService.getByUser(id, pageSize, pageNumber))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy tất cả danh sách và phân trang theo người đăng nhập", description = "")
    @GetMapping("user-login")
    public ResponseEntity<?> getAllOrderDetailByUserLogin(
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All OrderDetail By user id")
                .data(orderDetailService.getByUserLogin(pageSize, pageNumber))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy danh sách order detail theo id", description = "")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get OrderDetail by id")
                .data(orderDetailService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy danh sách order detail theo order id", description = "lấy danh sách order detail theo order id")
    @GetMapping("/order-id/{id}")
    public ResponseEntity<?> getOrderDetailByOrderId(@PathVariable("id") Long orderId) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get OrderDetail by id")
                .data(orderDetailService.getAllOrderId(orderId))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
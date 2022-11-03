package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.MainDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.OrderDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/orderDetail")
@Tag(description = "OrderDetail controller", name = "Các api về OrderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllOrderDetail() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All OrderDetail")
                .data(orderDetailService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderDetailAndPage(
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-number") Integer pageNumber) {
        Page<OrderDetailDto> page = orderDetailService.getAllAndPage(pageSize, pageNumber);
        return ResponseEntity.ok(DefaultPagingResponse.success(page));
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get OrderDetail by id")
                .data(orderDetailService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody OrderDetailDto orderDetailDto) {
        return ResponseEntity.ok(DefaultResponse.success(orderDetailService.create(orderDetailDto)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody OrderDetailDto orderDetailDto) {
        return ResponseEntity.ok(DefaultResponse.success(orderDetailService.update(id, orderDetailDto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        orderDetailService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/order")
@Tag(
        description = "Order controller",
        name = "Các api về hóa đơn"
)
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Đặt hàng", description = "Đặt hàng")
    @PostMapping("/check-out")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public ResponseEntity<?> checkoutOrder(@Valid @RequestBody OrderEntity order) throws MessagingException {
        return ResponseEntity.ok(DefaultResponse.success(orderService.checkoutOrder(order)));
    }


    @Operation(summary = "Xác nhận đơn đặt hàng", description = "Xác nhận đơn đặt hàng")
    @GetMapping("/order-confirm/{id}")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<?> orderConfirmed(
            @PathVariable("id") Long orderId
    ) {
        return ResponseEntity.ok(DefaultResponse.success(orderService.orderConfirmed(orderId)));
    }


    @Operation(summary = "Chờ thanh toán đơn đặt hàng", description = "Chờ thanh toán đơn đặt hàng")
    @GetMapping("/wait-for-pay/{id}")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<?> waitForPay(
            @PathVariable("id") Long orderId
    ) {
        return ResponseEntity.ok(DefaultResponse.success(orderService.waitForPay(orderId)));
    }

    @Operation(summary = "Đang vận chuyển đơn đặt hàng", description = "Đang vận chuyển đơn đặt hàng")
    @GetMapping("/being-shipped/{id}")
    @PreAuthorize("hasAuthority('STAFF')")
    public ResponseEntity<?> beingShipped(
            @PathVariable("id") Long orderId
    ) {
        return ResponseEntity.ok(DefaultResponse.success(orderService.beingShipped(orderId)));
    }


    @Operation(summary = "Hủy đơn đặt hàng", description = "Hủy đơn đặt hàng")
    @GetMapping("/cancelled/{id}")
    public ResponseEntity<?> cancelled(
            @PathVariable("id") Long orderId,
            @RequestParam String reason
    ) {
        return ResponseEntity.ok(DefaultResponse.success(orderService.cancelled(orderId, reason)));
    }


    //    list status order
    @Operation(summary = "Danh sách hóa đơn theo status", description = "Danh sách hóa đơn theo status")
    @GetMapping("/list-status/{status}")
    public ResponseEntity<?> listStatus(
            @PathVariable("status") OrderStatusEnum status
    ) {
        return ResponseEntity.ok(DefaultResponse.success(orderService.listStatus(status)));
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return ResponseEntity.ok(DefaultResponse.success(orderService.status()));
    }


}

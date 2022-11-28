package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.contants.OrderStatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CreateOrderReq;
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

    @Operation(summary = "Danh sách tất cả hóa đơn", description = "Danh sách tất cả hóa đơn")
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    public ResponseEntity<?> getAll()  {
        return ResponseEntity.ok(DefaultResponse.success(orderService.getAll()));
    }

    @Operation(summary = "Đặt hàng", description = "Đặt hàng")
    @PostMapping("/check-out")
    public ResponseEntity<?> checkoutOrder(@Valid @RequestBody CreateOrderReq order) throws MessagingException {
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
            @RequestParam() String reason
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


    @Operation(summary = "Đặt lại đơn hàng đã mua", description = "Đặt lại đơn hàng đã mua")
    @GetMapping("/re-order/{id}")
    public ResponseEntity<?> reOrder(
            @PathVariable("id") Long id
    ) {
        orderService.reOrder(id);
        return ResponseEntity.ok(DefaultResponse.success("Đặt lại đơn hàng thành công"));
    }

    @Operation(summary = "Đếm số lượng đơn hàng theo trạng thái và tài khoản người dùng", description = "Đếm số lượng đơn hàng theo trạng thái và tài khoản người dùng")
    @GetMapping("/count-order/{status}")
    public ResponseEntity<?> countOrder(
            @PathVariable("status") int status
    ) {
        return ResponseEntity.ok(DefaultResponse.success(orderService.countOrderStatus(status)));
    }


}

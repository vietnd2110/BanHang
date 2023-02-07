package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.OrderDetailDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderDetailEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.OrderDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.OrderService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/orderDetail")
@Tag(description = "OrderDetail controller", name = "Các api về OrderDetail")
@AllArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    private final OrderService orderService;


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

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable Long id,
                                               @RequestBody OrderDetailDto orderDetailDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Cập nhập thành công")
                .data(orderDetailService.update(id, orderDetailDto))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/create-order-detail/{idO}/{idP}")
    public ResponseEntity<?> createOrderDetail(
            @PathVariable("idO") Long idOrder,
            @PathVariable("idP") Long idProduct
            ) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Cập nhập thành công")
                .data(orderDetailService.addOrderDetail(idOrder, idProduct))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sum-price-order/{id}")
    public ResponseEntity<?> sumOrderDetail(
            @PathVariable("id") Long idOrder
    ) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Cập nhập thành công")
                .data(orderService.sumTotalOrderDetail(idOrder))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("delete/{orderDetailId}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable("orderDetailId") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Xóa thành công")
                .data(null)
                .build();
        orderDetailService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/deleteAll/{orderId}")
    public ResponseEntity<?> deleteAll(@PathVariable("orderId") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Xóa tất cả order detail theo  thành công")
                .data(null)
                .build();
        orderDetailService.deleteAllByOrderId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("updateQuantity/{productId}/order/{orderId}")
    public ResponseEntity updateQuantity(
            @PathVariable("productId") Long productId,
            @PathVariable("orderId") Long orderId,
            @RequestParam("quantity") Integer quantity
    ) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("update")
                .data(orderDetailService.updateQuantitys(productId, orderId, quantity))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





    @GetMapping("/updateQuantitys/{productId}/order/{orderId}")
    public ResponseEntity updateQuantityGet(
            @PathVariable("productId") Long productId,
            @PathVariable("orderId") Long orderId,
            @RequestParam("quantity") Integer quantity
    ) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("update")
                .data(orderDetailService.updateQuantity(productId, orderId, quantity))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/sumTotal/{orderId}")
    public ResponseEntity<?> sumTotal(@PathVariable("orderId") Long orderId) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Tổng tiền theo order thành công")
                .data(orderDetailService.total(orderId))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

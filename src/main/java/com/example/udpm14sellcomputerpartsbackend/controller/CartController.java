package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CartDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.CartService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "lấy danh sách giỏ hàng theo người dùng đăng nhập", description = "ahihi")
    @GetMapping("/user")
    public ResponseEntity<?> getCartByUser() {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Cart theo User")
                .data(cartService.getAllByUser(uDetailService.getId()))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(summary = "lấy thông tin tổng tiền giỏ hàng và số lượng sản phẩn trong giỏ hàng", description = "ahihi")
    @GetMapping("/sumTotalAndQuantity")
    public ResponseEntity<?> sumTotalAndQuantity() {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Success")
                .data(cartService.sumTotalPriceAndQuantity(uDetailService.getId()))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "lấy toàn bộ danh sách giỏ hàng", description = "")
    @GetMapping("/list")
    public ResponseEntity<?> getAllCart() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Cart")
                .data(cartService.getAllCart())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "thêm sản phẩm trong giỏ hàng", description = "request id sản phẩm")
    @PostMapping("/addToCart")
    public ResponseEntity addToCart(@RequestBody CartDto cartDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Add to cart")
                .data(cartService.addToCart(cartDto))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "cập nhập sản phẩm trong giỏ hàng", description =" param số lượng sản phẩm")
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity updateQuantity(
            @PathVariable("id") Long id,
            @RequestParam("quantity") Integer quantity
                                         ) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("update")
                .data(cartService.updateQuantity(id, quantity))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "xóa sản phẩm trong giỏ hàng", description = "xóa sản phẩm trong giỏ hàng")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Xóa thành công")
                .data(null)
                .build();
        cartService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

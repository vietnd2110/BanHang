package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CartDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.CartService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "lấy danh sách giỏ hàng theo người dùng đăng nhập", description = "ahihi")
    @PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Cart theo id")
                .data(cartService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Operation(summary = "lấy thông tin tổng tiền giỏ hàng và số lượng sản phẩn trong giỏ hàng", description = "ahihi")
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("isAuthenticated()")
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
    @Operation(summary = "xóa hêt sản phẩm trong giỏ hàng theo user", description = "xóa sản phẩm trong giỏ hàng")
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Xóa thành công")
                .data(null)
                .build();
        cartService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

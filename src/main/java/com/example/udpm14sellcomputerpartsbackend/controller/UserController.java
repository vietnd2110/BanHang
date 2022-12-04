package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.AuthService;
import com.example.udpm14sellcomputerpartsbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(DefaultResponse.success(userService.findById(id)));
    }

    @Operation(summary = "Lấy danh sách user theo staff", description = "")
    @GetMapping("/staff")
    public ResponseEntity<?> findByStaff(
            @RequestParam(value = "page",defaultValue = "0") Integer pageSize,
            @RequestParam(value = "page-number", required = false) Integer pageNumber
    ){
        return ResponseEntity.ok(DefaultResponse.success(userService.findAllByStaff(pageSize, pageNumber)));
    }

    @Operation(summary = "Lấy danh sách user theo Admin", description = "")
    @GetMapping("/admin")
    public ResponseEntity<?> findByAdmin(
            @RequestParam(value = "page",defaultValue = "0") Integer pageSize,
            @RequestParam(value = "page-number", required = false) Integer pageNumber
    ){
        return ResponseEntity.ok(DefaultResponse.success(userService.findAllByAdmin(pageSize, pageNumber)));
    }

    @Operation(summary = "Lấy danh sách user theo customer", description = "")
    @GetMapping("/customer")
    public ResponseEntity<?> findByCustomer(
            @RequestParam(value = "page",defaultValue = "0") Integer pageSize,
            @RequestParam(value = "page-number", required = false) Integer pageNumber
    ){
        return ResponseEntity.ok(DefaultResponse.success(userService.findAllByCustomer(pageSize, pageNumber)));
    }

    @PostMapping(value = "/update-image/{id}",consumes = "multipart/form-data")
    public ResponseEntity<?> updateImage(
            @PathVariable("id") Long id,
            @RequestBody MultipartFile file
    ){
        return ResponseEntity.ok(DefaultResponse.success("Update image success",userService.updateImage(id,file)));
    }
}
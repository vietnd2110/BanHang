package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerAccount(@RequestBody UserRegister userRegister) throws MessagingException {
        SampleResponse response = SampleResponse.builder()
                .status(true)
                .message("Đăng ký thành công")
                .data(userService.registerAccount(userRegister,new StringBuffer("http://localhost:8080/api/v1/auth/register/verifi?code=")))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/register/verify")
    public ResponseEntity<?> verifiCode(@RequestParam("code") String code){
        return ResponseEntity.ok(userService.verifiCode(code));
    }


}

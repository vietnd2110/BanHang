package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(DefaultResponse.success(authService.findByUserId(id)));
    }


}

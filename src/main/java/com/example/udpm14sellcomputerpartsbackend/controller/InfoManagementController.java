package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.InfoMangementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/api/v1/management")
public class InfoManagementController {
    private final InfoMangementService infoMangementService;

    public InfoManagementController(InfoMangementService infoMangementService) {
        this.infoMangementService = infoMangementService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") Long id, @RequestBody UserEntity userEntity) throws MessagingException {
        SampleResponse response = SampleResponse.builder()
                .status(true)
                .message("Thêm thông tin thành công")
                .data(infoMangementService.updateInfo(id, userEntity))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllInfo() throws MessagingException {
        SampleResponse response = SampleResponse.builder()
                .status(true)
                .message("Lấy thông tin của tài khoản")
                .data(infoMangementService.getInfoUser())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

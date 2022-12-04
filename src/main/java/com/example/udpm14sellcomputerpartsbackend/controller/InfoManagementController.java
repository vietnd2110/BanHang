package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.InfoManagementDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.InfoMangementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/management")
public class InfoManagementController {
    private final InfoMangementService infoMangementService;

    public InfoManagementController(InfoMangementService infoMangementService) {
        this.infoMangementService = infoMangementService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(
            @PathVariable("id") Long id,
            @ModelAttribute InfoManagementDto userEntity,
            @RequestParam(required = false)MultipartFile file) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Sửa thông tin thành công")
                .data(infoMangementService.updateInfo(id, userEntity,file))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllInfo() throws MessagingException {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin của tài khoản")
                .data(infoMangementService.getInfoUser())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(DefaultResponse.success(infoMangementService.findByUserId(id)));
    }
}

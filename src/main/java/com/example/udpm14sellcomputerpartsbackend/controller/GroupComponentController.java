package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.entity.GroupComponentEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.GroupComponent;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.GroupComponentService;
import com.example.udpm14sellcomputerpartsbackend.service.InfoMangementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/component")
public class GroupComponentController {

    private final GroupComponentService groupComponentService;

    @PostMapping("/create")
    public ResponseEntity<?> CreateComponent(@RequestBody GroupComponent groupComponent) throws MessagingException {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thêm component thành công")
                .data(groupComponentService.createComponent(groupComponent))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComponent(@PathVariable("id") Long id, @RequestBody GroupComponent groupComponent) throws MessagingException {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Sửa component thành công")
                .data(groupComponentService.updateComponent(id, groupComponent))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComponent(@PathVariable("id") Long id) throws MessagingException {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Xóa component thành công")
                .data(groupComponentService.deleteComponent(id))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getAllComponent() throws MessagingException {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin component")
                .data(groupComponentService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.GroupComponentDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.GroupComponentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/component")
public class GroupComponentController {

    private final GroupComponentService groupComponentService;

    @PostMapping("/create")
    public ResponseEntity<?> CreateComponent(@RequestBody GroupComponentDto groupComponent) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Thêm component thành công")
                .data(groupComponentService.createComponent(groupComponent))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComponent(@PathVariable("id") Long id, @RequestBody GroupComponentDto groupComponent) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Sửa component thành công")
                .data(groupComponentService.updateComponent(id, groupComponent))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComponent(@PathVariable("id") Long id) {
        groupComponentService.deleteComponent(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getAllComponent() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin component")
                .data(groupComponentService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity getComponentById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get Component By Id")
                .data(groupComponentService.findById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

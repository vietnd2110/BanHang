package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.GroupComponentDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.GroupComponentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/component")
public class GroupComponentController {

    private final GroupComponentService groupComponentService;

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody GroupComponentDto groupComponentDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(groupComponentService.createComponent(groupComponentDto)));
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody GroupComponentDto groupComponentDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(groupComponentService.updateComponent(id, groupComponentDto)));
    }


    @PreAuthorize("hasAuthority('ADMIN')")
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

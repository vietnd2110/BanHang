package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.StaffDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.StaffManagementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/staff")
public class StaffManagementController {

    private final StaffManagementService staffManagementService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllStaff() {
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("Get all staff")
                .data(staffManagementService.getAll())
                .build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStaff(
            @Valid @RequestBody StaffDto staffDto
    ) {
        return ResponseEntity.ok(
                DefaultResponse.success(staffManagementService.create(staffDto)
                ));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStaff(
            @PathVariable("id") Long id,
            @Valid @RequestBody StaffDto staffDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(staffManagementService.update(id, staffDto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable("id") Long id) {
        staffManagementService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

}

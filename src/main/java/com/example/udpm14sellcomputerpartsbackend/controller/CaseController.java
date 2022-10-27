package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.VgaDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.CaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/case")
@Tag(
        description = "Case controller",
        name = "Các api về Case"
)
public class CaseController {
    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }


    @GetMapping
    public ResponseEntity getAllChip() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Case")
                .data(caseService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody CaseDto caseDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(caseService.create(caseDto)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CaseDto caseDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(caseService.update(id, caseDto)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        caseService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }
}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.CaseDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.VgaDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.CaseRepository;
import com.example.udpm14sellcomputerpartsbackend.service.CaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/case")
@Tag(
        description = "Case controller",
        name = "Các api về Case"
)
@AllArgsConstructor
public class CaseController {
    private final CaseService caseService;
    private final CaseRepository caseRepository;


    @PreAuthorize("permitAll()")
    @GetMapping("/product-case/{id}")
    public ResponseEntity<?> listProductCase(
            @PathVariable(value = "id",required = false) Long categoryId,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "page-size") Integer pageSize
    ){
        return ResponseEntity.ok(DefaultPagingResponse.success(caseService.listProductCase(categoryId,page,pageSize)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneProductCase(
            @PathVariable("id") Long productId
    ){
        return ResponseEntity.ok(DefaultResponse.success(caseService.getOneProductCase(productId)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<?> getAllCase() {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Case")
                .data(caseService.getAll())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("product/{id}")
    public ResponseEntity<?> getAllCaseByProduct(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get All Case By Product id")
                .data(caseService.getByProductId(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCaseById(@PathVariable("id") Long id) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Get Case by id")
                .data(caseService.getById(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody CaseDto caseDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(caseService.create(caseDto)));
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CaseDto caseDto
    ) {
        return ResponseEntity.ok(DefaultResponse.success(caseService.update(id, caseDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        caseService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

    @GetMapping("/list-case")
    public ResponseEntity<?> listCase() {
        return ResponseEntity.ok(DefaultResponse.success(caseRepository.listCateProductCaseDto()));
    }
}

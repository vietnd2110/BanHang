package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.PcRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/pc")
public class PcController {
    private final PcRepository pcRepository;

    public PcController(PcRepository pcRepository) {
        this.pcRepository = pcRepository;
    }

    @GetMapping()
    public ResponseEntity<?> listProductPc(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(DefaultPagingResponse.success(pcRepository.listProductPc(PageRequest.of(page, pageSize))));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> listProductPcByProductId(
            @PathVariable("id")Long id
    ) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Lấy thông tin Brand ")
                .data(pcRepository.ProductPcByProductId(id))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> listProductPcByCategoryId(
            @PathVariable("id")Long id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "page-size") Integer pageSize
    ) {
        return ResponseEntity.ok(DefaultPagingResponse.success(pcRepository.listProductPcByCategory(id,PageRequest.of(page, pageSize))));
    }



    @GetMapping("/list-cate")
    public ResponseEntity<?> listProductCate() {
        return ResponseEntity.ok(DefaultResponse.success(pcRepository.listCateProductDto()));
    }

}

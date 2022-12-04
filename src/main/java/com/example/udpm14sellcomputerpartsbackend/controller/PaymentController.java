package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.dto.PaymentDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.OrderEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PaymentEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/payment")
@Tag(
        description = "Payment controller",
        name = "Các api về hình thức thanh toán"
)
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get all payment", description = "Get all payment")
    @GetMapping("/list")
    public ResponseEntity<?> getAll(
    ) {
        return ResponseEntity.ok(
                DefaultResponse.success(paymentService.paymentEntityList()));
    }

    @Operation(summary = "Get one payment", description = "Get one payment")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(DefaultResponse.success(paymentService.getOnePayment(id)));
    }


    @Operation(summary = "Create payment", description = "Create payment")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @ModelAttribute PaymentDto paymentDto,
            @RequestBody MultipartFile file
            ) {
        return ResponseEntity.ok(DefaultResponse.success(paymentService.create(paymentDto,file)));
    }

    @Operation(summary = "Update payment", description = "Update payment")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @ModelAttribute PaymentDto paymentDto,
            @RequestBody MultipartFile file
    ) {
        return ResponseEntity.ok(DefaultResponse.success(paymentService.update(id,paymentDto,file)));
    }

    @Operation(summary = "Delete payment", description = "Delete payment")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id) {
        paymentService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Detele success"));
    }



}

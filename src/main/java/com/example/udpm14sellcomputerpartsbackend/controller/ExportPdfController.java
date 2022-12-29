package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.service.ExportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/export")
@Tag(
        description = "Export PDF controller",
        name = "Các api về export"
)
public class ExportPdfController {

    @Autowired
    private ExportService exportService;

    @Operation(summary = "Export Order", description = "")
    @GetMapping("order/{id}")
    public ResponseEntity<?> exportPdfOrder(@PathVariable("id") Long idOrder) {
        ByteArrayInputStream byteArrayInputStream = exportService.exportPdfOrder(idOrder);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content", "inline; filename:order.pdf");

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteArrayInputStream));
    }

}

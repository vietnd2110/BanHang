package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.service.ExportService;
import com.example.udpm14sellcomputerpartsbackend.service.impl.ExportServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline; filename=order"+idOrder+"_"+currentDateTime+".pdf");

        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteArrayInputStream));
    }

}

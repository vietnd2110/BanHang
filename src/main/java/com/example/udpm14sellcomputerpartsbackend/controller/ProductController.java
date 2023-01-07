package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.contants.StatusEnum;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductDto;
import com.example.udpm14sellcomputerpartsbackend.model.dto.ProductImageDto;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultPagingResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ProductService;
import com.example.udpm14sellcomputerpartsbackend.ultil.QrUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Lấy tất cả danh sách san phẩm", description = "Lấy tất cả danh sách san phẩm")
    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam("page-size") Integer pageNumber
    ) {
       DefaultPagingResponse defaultPagingResponse =
               DefaultPagingResponse.success(productService.findAll(page,pageNumber));
        return ResponseEntity.ok(defaultPagingResponse);
    }

    @Operation(summary = "Danh sách product theo status", description = "Danh sách product theo status")
    @PreAuthorize("permitAll()")
    @GetMapping("/list-status/{status}")
    public ResponseEntity<?> listStatus(
            @PathVariable("status") StatusEnum status,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam("page-size") Integer pageNumber
    ) {
        return ResponseEntity.ok(DefaultPagingResponse.success(productService.listStatus(status,page,pageNumber)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOne(
            @PathVariable("id") Long id
    ){
       return ResponseEntity.ok(DefaultResponse.success(productService.getOne(id)));
    }

    @Operation(summary = "Lấy tất cả danh sách san phẩm product và ảnh theo id product bên product ", description = "Lấy tất cả danh sách san phẩm product và ảnh theo id product bên product ")
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> findAllByIdProduct(
            @PathVariable("id") Long id,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam("page-size") Integer pageNumber
    ){
      //  Page<ProductImageDto> productImageDtos = productService.findAllByIDProduct(id,page,pageSize);

        DefaultPagingResponse pagingResponse =
                DefaultPagingResponse.success(productService.findAllByIDProduct(id,page,pageNumber));
        return ResponseEntity.ok(pagingResponse);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",defaultValue = "0") Integer pageSize,
            @RequestParam(value = "page-size") Integer pageNumber
    ) {
        return ResponseEntity.ok(DefaultPagingResponse.success(
                productService.search(name,pageSize,pageNumber)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<?> getAllAndPage(
            @RequestParam(value = "page",defaultValue = "0") Integer pageSize,
            @RequestParam(value = "page-size") Integer pageNumber
    ) {
        return ResponseEntity.ok(DefaultPagingResponse.success(productService.getAllAndPage(pageSize, pageNumber)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getAllByCategory(
            @PathVariable("id") Long cid,
            @RequestParam(value = "page",defaultValue = "0") Integer pageSize,
            @RequestParam(value = "page-size") Integer pageNumber
    ) {
        return ResponseEntity.ok(
                DefaultPagingResponse.success(productService.findByCategory(cid, pageSize, pageNumber)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/product-id/{id}")
    public ResponseEntity<?> getByIdProducty(
            @PathVariable("id") Long cid,
            @RequestParam(value = "page",defaultValue = "0") Integer pageSize,
            @RequestParam(value = "page-size") Integer pageNumber
    ) {
        return ResponseEntity.ok(
                DefaultPagingResponse.success(productService.findByIdProduct(cid, pageSize, pageNumber)));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/brand/{id}")
    public ResponseEntity<?> getAllByBrand(
            @PathVariable("id") Long bid,
            @RequestParam(value = "page") Integer pageSize,
            @RequestParam(value = "page-size") Integer pageNumber
    ) {
        Page<ProductImageDto> page = productService.findByBrand(bid, pageSize, pageNumber);

        return ResponseEntity.ok(DefaultPagingResponse.success(page));

    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Update success")
                .data(productService.update(id, productDto))
                .build();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyAuthority('STAFF','ADMIN')")
    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDto productDto) {
        SampleResponse response = SampleResponse.builder()
                .success(true)
                .message("Create success")
                .data(productService.create(productDto))
                .build();
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.ok(DefaultResponse.success("Delete success"));
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "qrcode/{id}")
    public void qrcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(QrUtils.getQRCodeImage(id, 200, 200));
        outputStream.flush();
        outputStream.close();
    }
}

package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.payload.request.ColorRequest;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;
import com.example.udpm14sellcomputerpartsbackend.service.ColorService;
import com.example.udpm14sellcomputerpartsbackend.ultil.Constants;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/color")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public BaseResponse getAll() {
        return BaseResponse.success("Lấy danh sách thành công").withData(colorService.getAll());
    }

    @PostMapping()
    public BaseResponse create(@RequestBody ColorRequest colorRequest) {
        return BaseResponse.success("Thêm mới thành công").withData(colorService.create(colorRequest));
    }

    @DeleteMapping("/{id}")
    public BaseResponse delete(@PathVariable Long id) {
        colorService.delete(id);
        return BaseResponse.success(Constants.RESPONSE_CODE.DELETE_SUCCESS, "Xóa thành công");
    }
    @PutMapping("/{id}")
    public BaseResponse update(@PathVariable Long id, @RequestBody ColorRequest colorRequest){
        return BaseResponse.success("Cập nhập thành công").withData(colorService.update(colorRequest,id));
    }

}

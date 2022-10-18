package com.example.udpm14sellcomputerpartsbackend.contants;


import lombok.Getter;

@Getter
public enum ResponseStatusContants {

    ERROR(0,"Thất bại"),
    SUCCESS(200,"Thành công"),
    FORMAT_DATE(400,"Ngày kết thúc phải lớn hơn ngày hiện tại"),
    NOT_FOUND_REVIEW(100,"Review không tồn tại")
    ;

    private final Integer code;
    private final String message;

    ResponseStatusContants(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}

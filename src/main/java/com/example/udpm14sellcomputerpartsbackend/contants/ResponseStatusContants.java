package com.example.udpm14sellcomputerpartsbackend.contants;


import lombok.Getter;

@Getter
public enum ResponseStatusContants {

    ERROR(0,"Thất bại"),
    SUCCESS(1,"Thành công")
    ;

    private final Integer code;
    private final String message;

    ResponseStatusContants(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}

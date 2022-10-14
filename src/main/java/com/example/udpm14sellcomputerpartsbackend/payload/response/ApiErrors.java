package com.example.udpm14sellcomputerpartsbackend.payload.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErrors {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiErrors(HttpStatus status, String message, List<String> errors){
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

//    public ApiErrors(HttpStatus status , String message, String errors){
//        super();
//        this.status = status;
//        this.message = message;
//        errors = Arrays.asList(errors);
//    }




}

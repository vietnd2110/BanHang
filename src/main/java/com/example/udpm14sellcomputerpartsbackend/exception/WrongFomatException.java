package com.example.udpm14sellcomputerpartsbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WrongFomatException extends ProjectException{
    private int code;
    private String message;

    public WrongFomatException(String message){
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }
}

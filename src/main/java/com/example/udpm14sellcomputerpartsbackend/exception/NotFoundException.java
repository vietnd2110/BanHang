package com.example.udpm14sellcomputerpartsbackend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends ProjectException{

    public NotFoundException(int code, String message){
        super(code,message);
    }

}

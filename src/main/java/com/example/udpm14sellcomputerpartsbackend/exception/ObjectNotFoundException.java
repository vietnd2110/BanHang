package com.example.udpm14sellcomputerpartsbackend.exception;


import com.example.udpm14sellcomputerpartsbackend.contants.ResponseStatusContants;

public class ObjectNotFoundException extends ProjectException {

    public ObjectNotFoundException(ResponseStatusContants e) {
        super(e.getCode(), e.getMessage());
    }
}

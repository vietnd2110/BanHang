package com.example.udpm14sellcomputerpartsbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
}

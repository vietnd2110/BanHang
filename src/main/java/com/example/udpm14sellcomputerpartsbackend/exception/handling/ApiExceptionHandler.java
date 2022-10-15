package com.example.udpm14sellcomputerpartsbackend.exception.handling;

import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.exception.ProjectException;
import com.example.udpm14sellcomputerpartsbackend.exception.WrongFomatException;
import com.example.udpm14sellcomputerpartsbackend.payload.response.ApiErrors;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler{

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public SampleResponse handlerNotFoundExcetion(NotFoundException ex) {
        return SampleResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SampleResponse handlerBadRequestException(BadRequestException ex) {
        return SampleResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
    }

    @ExceptionHandler(value = WrongFomatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DefaultResponse<?> handlerWrongFormatException(WrongFomatException e) {
        return this.handleProjectException(e);
    }

    @ExceptionHandler(value = ProjectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DefaultResponse<?> handleProjectException(ProjectException e) {
        return DefaultResponse.error(e);
    }





}

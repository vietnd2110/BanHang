package com.example.udpm14sellcomputerpartsbackend.payload.response;

import com.example.udpm14sellcomputerpartsbackend.contants.ResponseStatusContants;
import com.example.udpm14sellcomputerpartsbackend.exception.ProjectException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponse <T>{

    private Integer success;
    private String message;
    private T data;

    public static <T> DefaultResponse <T> success(T body){
        DefaultResponse<T> response  = new DefaultResponse<>();
        response.setSuccess(ResponseStatusContants.SUCCESS.getCode());
        response.setMessage(ResponseStatusContants.SUCCESS.getMessage());
        response.setData(body);

        return response;
    }

    public static <T> DefaultResponse <T> success(String message){
        DefaultResponse<T> response  = new DefaultResponse<>();
        response.setSuccess(ResponseStatusContants.SUCCESS.getCode());
        response.setMessage(message);

        return response;
    }

    public static <T> DefaultResponse <T> success(String message, T body){
        DefaultResponse<T> response = new DefaultResponse<>();
        response.success(ResponseStatusContants.SUCCESS.getCode());
        response.setMessage(message);
        response.setData(body);
        return response;
    }



    public static <T> DefaultResponse <T> error(ProjectException e){
        DefaultResponse<T> response = new DefaultResponse<>();
        response.setSuccess(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }



}

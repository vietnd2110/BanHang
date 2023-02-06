package com.example.udpm14sellcomputerpartsbackend.payload.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegister {

    @NotBlank(message = "Username is not empty")
    @Size(min = 1,max = 20,message = "Username length from 1 to 20")
    private String username;

    @NotBlank(message = "Fullname is not empty")
    @Size(min = 1,max = 50,message = "Fullname length from 1 to 50")
    private String fullname;

    @NotBlank(message = "Email is not empty")
    @Email(message = "Email invalidate")
    private String email;

    @NotBlank(message = "Password is not empty")
    @Size(min = 1,max = 50,message = "Password length from 1 to 50")
    private String password;

    private String province;

    private String district;

    private String ward;


}

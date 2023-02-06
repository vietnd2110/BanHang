package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.RegexContants;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class InfoManagementDto {

    private Long id;

    @NotBlank(message = "Fullname is not empty")
    @Size(min = 1,max = 30,message = "Address length from 1 to 30")
    private String fullname;

    @NotBlank(message = "Email is not empty")
    @Email
    private String email;

    @NotBlank(message = "Phone number is not empty")
    @Pattern(regexp = RegexContants.PHONE_REGEX,message = "phone number validate failed")
    @Length(max = 10)
    private String phone;

    private String image;

    @NotBlank(message = "address is not empty")
    @Size(min = 1,max = 50,message = "Address length from 1 to 50")
    private String address;

    private String province;

    private String district;

    private String ward;

}

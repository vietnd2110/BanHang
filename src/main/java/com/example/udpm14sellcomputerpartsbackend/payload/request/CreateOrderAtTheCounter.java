package com.example.udpm14sellcomputerpartsbackend.payload.request;

import com.example.udpm14sellcomputerpartsbackend.contants.RegexContants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderAtTheCounter {

    @NotBlank(message = "fullname is not empty")
    private String fullname;

    @NotBlank(message = "province is not empty")
    private String province;

    @NotBlank(message = "district is not empty")
    private String district;

    @NotBlank(message = "ward is not empty")
    private String ward;

    @NotBlank
    private String address;

    @NotBlank(message = "Phone number is not empty")
    @Pattern(regexp = RegexContants.PHONE_REGEX,message = "phone number validate failed")
    @Length(max = 10)
    private String phone;

    private String description;

}

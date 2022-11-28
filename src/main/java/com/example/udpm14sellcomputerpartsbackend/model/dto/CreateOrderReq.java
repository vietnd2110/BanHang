package com.example.udpm14sellcomputerpartsbackend.model.dto;

import com.example.udpm14sellcomputerpartsbackend.contants.RegexContants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderReq {


    private String fullname;


    @NotBlank(message = "address is not empty")
    @Size(min = 1,max = 200,message = "Address length from 1 to 200")
    private String address;

    @NotBlank(message = "Phone number is not empty")
    @Pattern(regexp = RegexContants.PHONE_REGEX,message = "phone number validate failed")
    @Length(max = 10)
    private String phone;

    private String description;

    @JsonProperty("payment_id")
    private Long paymentId;


    @JsonProperty("coupon_code")
    private String couponCode;

}

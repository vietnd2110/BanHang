package com.example.udpm14sellcomputerpartsbackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String tokenType = "Bearer";
    private String username;
    private Long id;
    private String name = "Bearer";

    private String phone;

    private String province;

    private String district;

    private String ward;
    private Collection<? extends GrantedAuthority> role;

    public LoginResponse(String token, String fullName, Collection<? extends GrantedAuthority> authorities,String username,Long id, String phone, String province, String district, String ward) {
        this.token = token;
        this.name  = fullName;
        this.role  = authorities;
        this.username = username;
        this.id = id;
        this.phone = phone;
        this.province = province;
        this.district = district;
        this.ward = ward;
    }
}

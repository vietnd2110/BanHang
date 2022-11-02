package com.example.udpm14sellcomputerpartsbackend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

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
    private Collection<? extends GrantedAuthority> role;

    public LoginResponse(String token, String fullName, Collection<? extends GrantedAuthority> authorities,String username,Long id) {
        this.token = token;
        this.name  = fullName;
        this.role  = authorities;
        this.username = username;
        this.id = id;
    }
}

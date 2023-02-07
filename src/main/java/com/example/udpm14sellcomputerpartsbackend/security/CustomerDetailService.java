package com.example.udpm14sellcomputerpartsbackend.security;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CustomerDetailService implements UserDetails {

    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String image;
    private String address;
    private String province;
    private String district;
    private String ward;
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomerDetailService build(UserEntity userEntity){
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userEntity.getRole().toString()));
        return CustomerDetailService.builder()
                .id(userEntity.getId())
                .fullname(userEntity.getFullname())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .image(userEntity.getImage())
                .address(userEntity.getAddress())
                .province(userEntity.getProvince())
                .district(userEntity.getDistrict())
                .ward(userEntity.getWard())
                .authorities(authorities)
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

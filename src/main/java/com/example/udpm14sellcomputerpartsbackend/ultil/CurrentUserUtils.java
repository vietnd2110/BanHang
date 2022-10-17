package com.example.udpm14sellcomputerpartsbackend.ultil;

import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {

    public static CustomerDetailService getCurrentUserUtils(){
        return (CustomerDetailService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}

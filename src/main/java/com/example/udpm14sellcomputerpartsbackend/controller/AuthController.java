package com.example.udpm14sellcomputerpartsbackend.controller;


import com.example.udpm14sellcomputerpartsbackend.payload.request.ChangePassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ForgotPassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.LoginRequest;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.LoginResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.security.jwt.JwtProvider;
import com.example.udpm14sellcomputerpartsbackend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        description = "Authentication controller",
        name = "Các api về đăng nhập, đăng xuất"
)
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public AuthController(
            AuthService authService, UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtProvider jwtProvider) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Operation(summary = "Đăng ký tài khoản", description = "Đăng ký tài khoản")
    @PostMapping("/signup")
    public ResponseEntity<?> registerAccount(@RequestBody UserRegister userRegister) throws MessagingException {
        return ResponseEntity.ok(
                DefaultResponse.success(authService.registerAccount(userRegister, new StringBuffer("http://localhost:4200/register-verify/"))));
    }

    @Operation(summary = "Xác nhận email", description = "́Xác nhận email")
    @GetMapping("/register/verify")
    public ResponseEntity<?> verifiCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(
                DefaultResponse.success(authService.verifiCode(code)));
    }

    @Operation(summary = "Đổi mật khẩu", description = "Đổi mật khẩu")
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
        return ResponseEntity.ok(authService.changePassword(changePassword));
    }

    @Operation(summary = "Quên mật khẩu",description = "Quên mật khẩu")
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword forgotPassword) {
        return ResponseEntity.ok(authService.forgotPassword(forgotPassword));
    }

    @Operation(summary = "Đăng Nhập Tài Khoản",description = "Đăng nhập tài khoản")
    @PostMapping("/login-jwt")
    public ResponseEntity<?> loginJwt (@RequestBody LoginRequest loginRequest){
        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // Nếu k xảy ra exception tức là thong tin hợp lệ
        // Set thong tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String token = jwtProvider.createToken(authentication);
        CustomerDetailService customerDetailService = (CustomerDetailService) authentication.getPrincipal();

        return ResponseEntity.ok(
                SampleResponse
                        .builder()
                        .success(true)
                        .message("Login success")
                        .data(new LoginResponse(token,
                                                customerDetailService.getFullname(),
                                                customerDetailService.getAuthorities(),
                                                customerDetailService.getUsername(),
                                                customerDetailService.getId(),
                                                customerDetailService.getPhone(),
                                                customerDetailService.getProvince(),
                                                customerDetailService.getDistrict(),
                                                customerDetailService.getWard()
                                                ))
                        .build());

//        return ResponseEntity.ok(
//                new LoginResponse(token,customerDetailService.getFullname(),customerDetailService.getAuthorities(), customerDetailService.getUsername())).getBody();
    }
}

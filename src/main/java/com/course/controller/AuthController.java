package com.course.controller;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.service.AccountService;
import com.course.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AccountRequestDTO accountDto) {
    	AccountResponseDTO createdAccount = accountService.create(accountDto);
        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody com.course.model.AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getEmail());
                return ResponseEntity.ok(token);
            } else {
                throw new UsernameNotFoundException("Tài khoản không hợp lệ!");
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Sai email hoặc mật khẩu.");
        }
    }

    @GetMapping("/test")
    public String testAPI() {
        return "Hello world! (bảo vệ token nếu cần)";
    }
}


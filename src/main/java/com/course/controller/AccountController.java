package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.model.AuthRequest;
import com.course.service.AccountService;
import com.course.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
	@Autowired
    private AccountService service;
	@Autowired
    private JwtService jwtService;
	@Autowired
    private AuthenticationManager authenticationManager;
    
    @GetMapping("/test")
    public String testAPI() {
    	return "hello world";
    }

    @PostMapping("/addNewUser")
    public AccountResponseDTO addNewUser(@RequestBody AccountRequestDTO account) {
        return service.create(account);
    }

     @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}

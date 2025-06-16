package com.course.controller;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.model.Account;
import com.course.model.AuthRequest;
import com.course.service.AccountService;
import com.course.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private AccountService service;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Account account) {
        return service.create(account);
    }

     @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}

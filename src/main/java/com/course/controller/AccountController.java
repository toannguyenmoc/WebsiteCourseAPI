package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.dto.CommentRequestDTO;
import com.course.model.Account;
import com.course.model.AuthRequest;
import com.course.service.AccountService;
import com.course.service.CommentService;
import com.course.service.JwtService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
	@Autowired
    private AccountService accountService;
	
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body( accountService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body( accountService.findById(id));
    }
//    @PostMapping
//    public ResponseEntity<?> create(@Valid @RequestBody AccountRequestDTO dto) {
//        return ResponseEntity.ok().body( accountService.create(dto));
//    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id , @Valid @RequestBody AccountRequestDTO  dto) {
    	
        return ResponseEntity.ok().body( accountService.update(id , dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body( accountService.deleteById(id));
    }
   
    

}

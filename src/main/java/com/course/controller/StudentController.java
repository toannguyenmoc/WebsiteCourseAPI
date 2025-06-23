package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.service.AccountService;
import com.course.model.Account;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public ResponseEntity<?> getPagedStudent(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
	 return accountService.getPagedStudent(page, size);
	}
	
	@GetMapping("/change-status/{id}")
	public ResponseEntity<?> changeStatus(@PathVariable("id") Integer id) {
        AccountResponseDTO updatedAccount = accountService.changeStatus(id);
        return ResponseEntity.ok(updatedAccount);
    }
	

}

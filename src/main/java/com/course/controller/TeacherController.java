package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.service.AccountService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	@Autowired
	private AccountService accountService;
	
	 @GetMapping
	    public ResponseEntity<?> getPagedTeacher(
	            @RequestParam(name = "page", defaultValue = "0") int page,
	            @RequestParam(name = "size", defaultValue = "5") int size) {
		 return accountService.getPagedTeacher(page, size);
	 }
	 
	 
}

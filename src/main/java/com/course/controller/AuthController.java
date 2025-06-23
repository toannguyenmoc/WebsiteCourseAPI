package com.course.controller;

import com.course.dto.AuthRequestDTO;
import com.course.dto.AuthResponseDTO;
import com.course.model.AccountToChangePassword;
import com.course.model.AuthRequest;
import com.course.service.AccountService;
import com.course.service.EmailService;
import com.course.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private EmailService emailService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody AuthRequestDTO accountDto) {
		try {
			AuthResponseDTO createdAccount = accountService.create(accountDto);
			emailService.sendWelcomeEmail(accountDto.getEmail());
			
			return ResponseEntity.ok(createdAccount);
		} catch (Exception e) {
		    e.printStackTrace(); 
		    return ResponseEntity.status(403).body("Đã xảy ra lỗi khi tạo tài khoản hoặc gửi email.");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody com.course.model.AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

			if (authentication.isAuthenticated()) {
				boolean active = accountService.checkActive(authRequest.getEmail());
//				boolean active = true;
				
				if (active) {
					String token = jwtService.generateToken(authRequest.getEmail());
					return ResponseEntity.ok(token);
				} else {
					return ResponseEntity.status(403).body("Tài khoản bị khóa");
				}
			} else {
				throw new UsernameNotFoundException("Tài khoản không hợp lệ!");
			}
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(403).body("Sai email hoặc mật khẩu.");
		}
	}

	 
    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> changePasswoud(@PathVariable("id") Integer id , @Valid @RequestBody AccountToChangePassword account) {
    	 try {
    	        Authentication authentication = authenticationManager.authenticate(
    	                new UsernamePasswordAuthenticationToken(account.getEmail(), account.getOldPassword()));

    	        if (authentication.isAuthenticated()) {
    	            return ResponseEntity.ok().body(accountService.updatePassword(id, account.getNewPassword()));
    	        } else {
    	            return ResponseEntity.status(403).body("Sai email hoặc mật khẩu.");
    	        }

    	    } catch (BadCredentialsException e) {
    	        return ResponseEntity.status(403).body("Sai email hoặc mật khẩu.");
    	    } catch (Exception e) {
    	        return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
    	    }
    	
    }

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getEmail());
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}
}

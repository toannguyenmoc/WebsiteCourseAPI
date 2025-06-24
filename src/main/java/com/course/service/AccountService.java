package com.course.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.dto.AuthRequestDTO;
import com.course.dto.AuthResponseDTO;
import com.course.model.Account;

public interface AccountService {

    public AuthResponseDTO create(AuthRequestDTO account);

    public List<AccountResponseDTO> findAll();

    public AccountResponseDTO update(Integer id, AccountRequestDTO account);

    public AccountResponseDTO findById(Integer id);

    public AccountResponseDTO deleteById(Integer id);
    
    public AccountResponseDTO findByEmail(String email);

    public UserDetails loadUserByUsername(String email);
    
    public boolean checkActive(String email);
    
    public ResponseEntity<?> getPagedStudent(int page, int size);
    
    public ResponseEntity<?> getPagedTeacher(int page, int size);
    
    public AccountResponseDTO changeStatus(Integer accountId);
    
    public AccountResponseDTO updatePassword(Integer id, String password);
    
    public AccountResponseDTO forgotPassword(String email, String password);
}

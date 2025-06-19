package com.course.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.model.Account;

public interface AccountService {

    public AccountResponseDTO create(AccountRequestDTO account);

    public List<AccountResponseDTO> findAll();

    public AccountResponseDTO update(Integer id, AccountRequestDTO account);

    public AccountResponseDTO findById(Integer id);

    public AccountResponseDTO deleteById(Integer id);

    public UserDetails loadUserByUsername(String email);
    
    public boolean checkActive(String email);
    
    public ResponseEntity<?> getPagedStudent(int page, int size);
    
    public ResponseEntity<?> getPagedTeacher(int page, int size);
    
    public Account changeStatus(Integer accountId);
}

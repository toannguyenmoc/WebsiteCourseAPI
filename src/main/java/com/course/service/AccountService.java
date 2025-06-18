package com.course.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;

public interface AccountService {

    public AccountResponseDTO create(AccountRequestDTO account);

    public List<AccountResponseDTO> findAll();

    public AccountResponseDTO update(AccountRequestDTO account);

    public AccountResponseDTO findById(Integer id);

    public AccountResponseDTO deleteById(Integer id);

    public UserDetails loadUserByUsername(String email);
}

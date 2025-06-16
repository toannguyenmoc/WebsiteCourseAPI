package com.course.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.course.model.Account;

@Service
public interface AccountService {

    public Account create(Account account);

    public List<Account> findAll();

    public Account update(Account account);

    public Account findById(Integer id);

    public Account deleteById(Integer id);

    public UserDetails loadUserByUsername(String email);
}

package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.Account;

@Service
public interface AccountService {
	
	public Account create(Account account);                   
    public List<Account> findAll();                            
    public Optional<Account> update(Account account);
    public Optional<Account> findById(Integer id);              
    public void deleteById(Integer id); 
}

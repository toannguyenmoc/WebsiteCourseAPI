package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
import com.course.model.Account;
import com.course.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> userDetail = accountRepository.findByEmail(email);

        // Converting UserInfo to UserDetails
        return userDetail.map(AccountDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    @Override
    public Account create(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account update(Account account) {
        findById(account.getId());
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("không tìm thấy"));
    }

    @Override
    public Account deleteById(Integer id) {
        Account account = findById(id);
        accountRepository.deleteById(id);
        return account;
    }

}

package com.course.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.AccountMapper;
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

	        return userDetail.map(AccountDetails::new)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
	    }

	    @Override
	    public AccountResponseDTO create(AccountRequestDTO dto) {
	        Account account = AccountMapper.toEntity(dto);
	        account.setPassword(encoder.encode(dto.getPassword()));
	        Account saved = accountRepository.save(account);
	        return AccountMapper.toResponse(saved);
	    }

	    @Override
	    public List<AccountResponseDTO> findAll() {
	        List<Account> accounts = accountRepository.findAll()
	                .stream()
	                .sorted(Comparator.comparing(Account::getRegisteredDate).reversed())
	                .collect(Collectors.toList());
	        return AccountMapper.toResponseDTOList(accounts);
	    }

	    @Override
	    public AccountResponseDTO update(AccountRequestDTO dto) {
	    	Account account = AccountMapper.toEntity(dto);
	        findById(account.getId()); // kiểm tra tồn tại
	        Account updated = accountRepository.save(account);
	        return AccountMapper.toResponse(updated);
	    }

	    @Override
	    public AccountResponseDTO findById(Integer id) {
	        Account account = accountRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản với ID: " + id));
	        return AccountMapper.toResponse(account);
	    }

	    @Override
	    public AccountResponseDTO deleteById(Integer id) {
	        AccountResponseDTO acc = findById(id);
	        accountRepository.deleteById(id);
	        return acc;
	    }

}

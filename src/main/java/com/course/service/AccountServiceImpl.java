package com.course.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.dto.AuthRequestDTO;
import com.course.dto.AuthResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.AccountMapper;
import com.course.mapper.RegisterMapper;
import com.course.model.Account;
import com.course.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final LessonService lessonService;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder encoder;

	
    AccountServiceImpl(LessonService lessonService) {
        this.lessonService = lessonService;
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Account> userDetail = accountRepository.findByEmail(email);

		return userDetail.map(AccountDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
	}

	@Override
	public AuthResponseDTO create(AuthRequestDTO dto) {
		Account account = RegisterMapper.toEntity(dto);
		account.setPassword(encoder.encode(dto.getPassword()));
		Account saved = accountRepository.save(account);
		return RegisterMapper.toResponse(saved);
	}

	@Override
	public List<AccountResponseDTO> findAll() {
		List<Account> accounts = accountRepository.findAll().stream()
				.sorted(Comparator.comparing(Account::getRegisteredDate).reversed()).collect(Collectors.toList());
		return AccountMapper.toResponseDTOList(accounts);
	}

	@Override
	public AccountResponseDTO update(Integer id, AccountRequestDTO dto) {
		AccountResponseDTO accountFindById = findById(id); 
		Account account = AccountMapper.toEntity(dto);
		account.setId(id);
		account.setPassword(accountFindById.getPassword());
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

	@Override
	public ResponseEntity<?> getPagedStudent(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		//Tìm theo quyền
		Page<Account> students = accountRepository.findByRole(3, pageable);
		Page<AccountResponseDTO> result = students.map(AccountMapper::toResponse);
		Map<String, Object> response = new HashMap<>();
		response.put("data", result.getContent());
		response.put("currentPage", result.getNumber());
		response.put("totalItems", result.getTotalElements());
		response.put("totalPages", result.getTotalPages());

		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<?> getPagedTeacher(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		//Tìm theo quyền
		Page<Account> students = accountRepository.findByRole(2, pageable);
		Page<AccountResponseDTO> result = students.map(AccountMapper::toResponse);

		Map<String, Object> response = new HashMap<>();
		response.put("data", result.getContent());
		response.put("currentPage", result.getNumber());
		response.put("totalItems", result.getTotalElements());
		response.put("totalPages", result.getTotalPages());

		return ResponseEntity.ok(response);
	}

	@Override
	public boolean checkActive(String email) {
		Account user = accountRepository.findByEmail(email).get();
		return user.getActive();
	}
	
	public AccountResponseDTO changeStatus(Integer accountId) {
		Account account = accountRepository.findById(accountId).get();
        account.setActive(!account.getActive()); // đảo trạng thái
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.toResponse(updatedAccount);
    }

	@Override
	public AccountResponseDTO findByEmail(String email) {
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản với email: " + email));
		return AccountMapper.toResponse(account);
	}

	@Override
	public AccountResponseDTO updatePassword(Integer id, String password) {
		Account account = accountRepository.findById(id).get();
		account.setPassword(encoder.encode(password));
		Account updatedAccount = accountRepository.save(account);
		return AccountMapper.toResponse(updatedAccount);
	}

	
}

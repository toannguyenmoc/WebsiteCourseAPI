package com.course.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.course.dto.AuthRequestDTO;
import com.course.dto.AuthResponseDTO;
import com.course.model.Account;

public class RegisterMapper {
	public static Account toEntity(AuthRequestDTO dto) {
		Account account = new Account();
		account.setEmail(dto.getEmail());
		account.setPassword(dto.getPassword()); 
		account.setFullname(dto.getFullname());
		account.setRole(dto.getRole());
		account.setActive(true);
		return account;
	}

	public static AuthResponseDTO toResponse(Account account) {
		AuthResponseDTO dto = new AuthResponseDTO();
		dto.setEmail(account.getEmail());
		dto.setFullname(account.getFullname());
//		dto.setPassword(account.getPassword());
		dto.setRole(account.getRole());
		dto.setActive(account.getActive());
		return dto;
	}

	public static List<AuthResponseDTO> toResponseDTOList(List<Account> accounts) {
		return accounts.stream().map(RegisterMapper::toResponse).collect(Collectors.toList());
	}
}

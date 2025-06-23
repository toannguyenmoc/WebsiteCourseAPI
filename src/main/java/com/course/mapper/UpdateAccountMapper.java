package com.course.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.course.dto.AccountRequestDTO;
import com.course.dto.AccountResponseDTO;
import com.course.model.Account;

public class UpdateAccountMapper {
	public static Account toEntity(AccountRequestDTO dto) {
		Account account = new Account();
		account.setFullname(dto.getFullname());
		account.setGender(dto.getGender());
		account.setBirthday(dto.getBirthday());
		account.setAvatar(dto.getAvatar());
		return account;
	}

	public static AccountResponseDTO toResponse(Account account) {
		AccountResponseDTO dto = new AccountResponseDTO();
		dto.setId(account.getId());
		dto.setEmail(account.getEmail());
		dto.setFullname(account.getFullname());
		dto.setGender(account.getGender());
		dto.setBirthday(account.getBirthday());
		dto.setAvatar(account.getAvatar());
		dto.setRole(account.getRole());
		dto.setActive(account.getActive());
		dto.setRegisteredDate(account.getRegisteredDate());
		return dto;
	}

	public static List<AccountResponseDTO> toResponseDTOList(List<Account> accounts) {
		return accounts.stream().map(AccountMapper::toResponse).collect(Collectors.toList());
	}
}

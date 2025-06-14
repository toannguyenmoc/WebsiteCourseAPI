package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}

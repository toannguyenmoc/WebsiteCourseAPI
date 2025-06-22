package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	boolean existsByTransactionCode(String transactionCode);
	boolean existsByAccountIdAndCourseId(Integer accountId, Integer courseId);
}

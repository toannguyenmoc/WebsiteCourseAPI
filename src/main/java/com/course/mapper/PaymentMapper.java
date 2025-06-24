package com.course.mapper;

import java.util.Date;


import com.course.dto.PaymentRequestDTO;
import com.course.dto.PaymentResponseDTO;
import com.course.model.Account;

import com.course.model.Course;

import com.course.model.Payment;

public class PaymentMapper {

	public static Payment toEntity(PaymentRequestDTO dto) {
		Payment payment = new Payment();

		payment.setTotalAmount(dto.getTotalAmount());
		payment.setDiscount(dto.getDiscount());
		payment.setTransactionCode(dto.getTransactionCode());
		payment.setRegistrationDate(dto.getRegistrationDate() != null ? dto.getRegistrationDate() : new Date());
	    
	    Account account = new Account();
	    account.setId(dto.getAccountId());
	    payment.setAccount(account);

	    Course course = new Course();
	    course.setId(dto.getCourseId());
	    payment.setCourse(course);

	   

	    return payment;
	}
	 public static PaymentResponseDTO toResponse(Payment payment) {
		 PaymentResponseDTO dto = new PaymentResponseDTO();
	        dto.setId(payment.getId());
	        dto.setTotalAmount(payment.getTotalAmount());
	        dto.setDiscount(payment.getCourse().getCommission().getPercentage());
	        dto.setTransactionCode(payment.getTransactionCode());
	        dto.setRegistrationDate(payment.getRegistrationDate());
	      

	        if (payment.getCourse() != null) {
	            dto.setCourseId(payment.getCourse().getId());
	            dto.setCourseName(payment.getCourse().getTitle());
	        }
	        
	        if (payment.getAccount() != null) {
	            dto.setStudentId(payment.getAccount().getId());
	            dto.setAccountStudentName(payment.getAccount().getFullname());
	        }
	        if (payment.getAccount() != null) {
	           
	            dto.setAccountTeacherName(payment.getCourse().getAccount().getFullname());
	        }
	        
	       
	      

	        return dto;
	    }
	
		 
		 
		 
	 }


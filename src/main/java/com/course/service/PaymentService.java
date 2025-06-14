package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.dto.CourseRequestDTO;
import com.course.dto.PaymentRequestDTO;
import com.course.dto.PaymentResponseDTO;
import com.course.model.Payment;

@Service
public interface PaymentService {
    
    public PaymentResponseDTO create(PaymentRequestDTO paymentRequestDTO);                   
    public List<PaymentResponseDTO> findAll();                            
    public PaymentResponseDTO update(Integer id, PaymentRequestDTO paymentRequestDTO);
    public PaymentResponseDTO findById(Integer id);              
    public PaymentResponseDTO deleteById(Integer id); 
}

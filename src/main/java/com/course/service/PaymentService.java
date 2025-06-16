package com.course.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.PaymentRequestDTO;
import com.course.dto.PaymentResponseDTO;

@Service
public interface PaymentService {
    
    public PaymentResponseDTO create(PaymentRequestDTO paymentRequestDTO);                   
    public List<PaymentResponseDTO> findAll();                            
    public PaymentResponseDTO update(Integer id, PaymentRequestDTO paymentRequestDTO);
    public PaymentResponseDTO findById(Integer id);              
    public PaymentResponseDTO deleteById(Integer id); 
    public ResponseEntity<?> getPagedPayment(int page, int size);
}

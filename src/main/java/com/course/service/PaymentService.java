package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.Payment;

@Service
public interface PaymentService {
    
    public Payment create(Payment payment);                   
    public List<Payment> findAll();                            
    public Optional<Payment> update(Payment payment);
    public Optional<Payment> findById(Integer id);              
    public void deleteById(Integer id); 
}

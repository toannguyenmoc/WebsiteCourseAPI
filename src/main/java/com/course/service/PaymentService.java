package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.model.Payment;

@Service
public interface PaymentService {
    
    public Payment create(Payment payment);                   
    public List<Payment> findAll();                            
    public Payment update(Payment payment);
    public Payment findById(Integer id);              
    public Payment deleteById(Integer id); 
}

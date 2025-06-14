package com.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
import com.course.model.Payment;
import com.course.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment update(Payment payment) {
    	findById(payment.getId());
        return paymentRepository.save(payment);
    }

    @Override
    public Payment findById(Integer id) {
    	return paymentRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy"));
    }

    @Override
    public Payment deleteById(Integer id) {
    	Payment payment = findById(id);
    	paymentRepository.deleteById(id);
        return payment;
    }
}

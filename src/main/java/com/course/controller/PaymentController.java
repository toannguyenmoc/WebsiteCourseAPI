package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.course.dto.PaymentRequestDTO;
import com.course.dto.PaymentResponseDTO;

import com.course.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	  @Autowired private PaymentService paymentService;

	    @GetMapping
	    public ResponseEntity<List<PaymentResponseDTO>> getAllPayment() {
	        return ResponseEntity.ok(paymentService.findAll());
	    }
	    @GetMapping("/{id}")
	    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable("id") Integer id) {
	        return ResponseEntity.ok(paymentService.findById(id));
	    }
	    @PostMapping
	    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO dto) {
	    	return ResponseEntity.ok(paymentService.create(dto));
	    }
	    @PutMapping("/{id}")
	    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable("id") Integer id, @Valid @RequestBody PaymentRequestDTO dto) {
	    	return ResponseEntity.ok(paymentService.update(id, dto)); 
	    }
	    @DeleteMapping("/{id}")
	    public ResponseEntity<PaymentResponseDTO> deletePayment(@PathVariable("id") Integer id) {
	        return ResponseEntity.ok(paymentService.deleteById(id));
	    }

}

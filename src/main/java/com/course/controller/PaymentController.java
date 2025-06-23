package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.PaymentRequestDTO;
import com.course.dto.PaymentResponseDTO;
import com.course.service.PaymentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	// @GetMapping
	// public ResponseEntity<List<PaymentResponseDTO>> getAllPayment() {
	// return ResponseEntity.ok(paymentService.findAll());
	// }
	@GetMapping
	public ResponseEntity<?> getPagedCommission(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		return paymentService.getPagedPayment(page, size);
	}

	@GetMapping("/check/course")
	public ResponseEntity<?> getExistPayment(@RequestParam("accountId") Integer accountId,
			@RequestParam("courseId") Integer courseId) {
		return ResponseEntity.ok(paymentService.existsByAccountIdAndCourseId(accountId, courseId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(paymentService.findById(id));
	}

	@PostMapping
	public ResponseEntity<PaymentResponseDTO> createPayment(HttpServletRequest request,
			@Valid @RequestBody PaymentRequestDTO dto) {
		return ResponseEntity.ok(paymentService.create(request, dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable("id") Integer id,
			@Valid @RequestBody PaymentRequestDTO dto) {
		return ResponseEntity.ok(paymentService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PaymentResponseDTO> deletePayment(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(paymentService.deleteById(id));
	}

}

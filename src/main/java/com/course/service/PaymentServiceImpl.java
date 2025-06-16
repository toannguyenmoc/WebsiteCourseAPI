package com.course.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.PaymentRequestDTO;
import com.course.dto.PaymentResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.PaymentMapper;
import com.course.model.Payment;
import com.course.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
	public PaymentResponseDTO create(PaymentRequestDTO paymentRequestDTO) {
		Payment payment = PaymentMapper.toEntity(paymentRequestDTO);
		Payment saved = paymentRepository.save(payment);
		PaymentResponseDTO paymentResponseDTO = PaymentMapper.toResponse(saved);
		return paymentResponseDTO;
	}
    @Override
    public List<PaymentResponseDTO> findAll() {
		List<PaymentResponseDTO> responseList = paymentRepository.findAll().stream().map(PaymentMapper::toResponse)
				.sorted(Comparator.comparing(PaymentResponseDTO::getTotalAmount).reversed()
						.thenComparing(PaymentResponseDTO::getDiscount).reversed()
						.thenComparing(PaymentResponseDTO::getTransactionCode)
						.thenComparing(PaymentResponseDTO::getRegistrationDate))
				.collect(Collectors.toList());
		return responseList;
    }

    @Override
    public PaymentResponseDTO update(Integer id, PaymentRequestDTO paymentRequestDTO) {
		findById(id);
		Payment updatedPayment = PaymentMapper.toEntity(paymentRequestDTO);
		updatedPayment.setId(id);
		Payment update = paymentRepository.save(updatedPayment);
		PaymentResponseDTO paymentResponseDTO = PaymentMapper.toResponse(update);
		return paymentResponseDTO;
    }

    @Override
    public PaymentResponseDTO findById(Integer id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy khoá học"));
		PaymentResponseDTO paymentResponseDTO = PaymentMapper.toResponse(payment);
		return paymentResponseDTO;
    }

  

    @Override
    public PaymentResponseDTO deleteById(Integer id) {
    	PaymentResponseDTO paymentResponseDTO = findById(id);
    	paymentRepository.deleteById(id);
		return paymentResponseDTO;
    }
    @Override
    public ResponseEntity<?> getPagedPayment(int page, int size) {
    	Pageable pageable = PageRequest.of(page, size, Sort.by("registrationDate").descending());
    	  Page<Payment> list = paymentRepository.findAll(pageable);
  	    Page<PaymentResponseDTO> result = list.map(PaymentMapper::toResponse);


        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok(response);
    }
}

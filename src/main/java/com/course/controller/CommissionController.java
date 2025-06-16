package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.dto.CommissionRequestDTO;
import com.course.dto.CommissionResponseDTO;

import com.course.service.CommissionService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/commission")
public class CommissionController {
	 @Autowired private CommissionService commissionService;

	    @GetMapping
	    public ResponseEntity<List<CommissionResponseDTO>> getAllCommission() {
	        return ResponseEntity.ok(commissionService.findAll());
	    }
	    @GetMapping("/{id}")
	    public ResponseEntity<CommissionResponseDTO> getCommissionTypeById(@PathVariable("id") Integer id) {
	        return ResponseEntity.ok(commissionService.findById(id));
	    }
	    @PostMapping
	    public ResponseEntity<CommissionResponseDTO> createCommission(@Valid @RequestBody CommissionRequestDTO dto) {
	    	return ResponseEntity.ok(commissionService.create(dto));
	    }
	    @PutMapping("/{id}")
	    public ResponseEntity<CommissionResponseDTO> updateCommission(@PathVariable("id") Integer id, @Valid @RequestBody CommissionRequestDTO dto) {
	    	return ResponseEntity.ok(commissionService.update(id, dto)); 
	    }
	    @DeleteMapping("/{id}")
	    public ResponseEntity<CommissionResponseDTO> deleteCommission(@PathVariable("id") Integer id) {
	        return ResponseEntity.ok(commissionService.deleteById(id));
	    }

}

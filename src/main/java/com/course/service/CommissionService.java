package com.course.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.CommissionRequestDTO;
import com.course.dto.CommissionResponseDTO;


@Service
public interface CommissionService {
    
    public CommissionResponseDTO create(CommissionRequestDTO commission);                   
    public List<CommissionResponseDTO> findAll();                            
    public CommissionResponseDTO update(Integer id, CommissionRequestDTO commissionRequestDTO);
    public CommissionResponseDTO findById(Integer id);              
    public CommissionResponseDTO deleteById(Integer id); 
    public ResponseEntity<?> getPagedCommission(int page, int size);
}

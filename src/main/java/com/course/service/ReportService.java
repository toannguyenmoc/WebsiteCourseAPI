package com.course.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.ReportRequestDTO;
import com.course.dto.ReportResponseDTO;

@Service
public interface ReportService {

    public ReportResponseDTO create(ReportRequestDTO reportRequestDTO);                   
    public List<ReportResponseDTO> findAll();                            
    public ReportResponseDTO update(Integer id, ReportRequestDTO reportRequestDTO);
    public ReportResponseDTO findById(Integer id);              
    public ReportResponseDTO deleteById(Integer id); 
    public ResponseEntity<?> getPagedReports(int page, int size);
}

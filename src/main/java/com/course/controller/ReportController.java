package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.service.ReportService;
import jakarta.validation.Valid;

import com.course.dto.ReportRequestDTO;
import com.course.dto.ReportResponseDTO;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportResponseDTO>> getAllReports() {
        return ResponseEntity.ok(reportService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> getReportById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(reportService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReportResponseDTO> createReport(@Valid @RequestBody ReportRequestDTO dto) {
        return ResponseEntity.ok(reportService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> updateReport(@PathVariable("id") Integer id, @Valid @RequestBody ReportRequestDTO dto) {
        return ResponseEntity.ok(reportService.update(id, dto)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> deleteReport(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(reportService.deleteById(id));
    }

}

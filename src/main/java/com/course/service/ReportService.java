package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.Report;

@Service
public interface ReportService {
    
    public Report create(Report report);                   
    public List<Report> findAll();                            
    public Optional<Report> update(Report report);
    public Optional<Report> findById(Integer id);              
    public void deleteById(Integer id); 
}

package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.model.Report;

@Service
public interface ReportService {
    
    public Report create(Report report);                   
    public List<Report> findAll();                            
    public Report update(Report report);
    public Report findById(Integer id);              
    public Report deleteById(Integer id); 
}
